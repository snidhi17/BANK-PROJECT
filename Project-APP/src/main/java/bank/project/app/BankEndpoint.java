package bank.project.app;

import bank.project.dao.BankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap.project.bank.ListLoanRequest;
import soap.project.bank.ListLoanResponse;
import soap.project.bank.LoanScheme;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class BankEndpoint {
    private static final String url="http://bank.project.soap";
    private Logger logger= LoggerFactory.getLogger(BankEndpoint.class);

    @Autowired
    private BankService bankService;

    @PayloadRoot(namespace = url,localPart = "listLoanRequest")//
    @ResponsePayload
    public ListLoanResponse listLoanResponse(@RequestPayload ListLoanRequest listLoansRequest){
        ListLoanResponse response=new ListLoanResponse();

        List<bank.project.dao.LoanScheme> loanSchemeList = bankService.listloanAll();// pojo objects
        logger.info(loanSchemeList.toString());
        List<soap.project.bank.LoanScheme> loansList=new ArrayList<>();// xml list of objects as of its empty

        Iterator<bank.project.dao.LoanScheme> it= loanSchemeList.iterator();
        while(it.hasNext()){
            soap.project.bank.LoanScheme loan = new soap.project.bank.LoanScheme();// XSD POJO
            BeanUtils.copyProperties(it.next(),loan);
            loansList.add(loan);
        }

        response.getLoan().addAll(loansList);
        logger.info(response.getLoan().size()+"" + response.getLoan().get(0));
        return response;
    }
}