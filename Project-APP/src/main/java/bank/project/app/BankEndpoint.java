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

    @PayloadRoot(namespace = url,localPart = "listLoanRequest")
    @ResponsePayload
         // handles a SOAP web service request with the local part "listLoanRequest".
    public ListLoanResponse listLoanResponse(@RequestPayload ListLoanRequest listLoansRequest){
        ListLoanResponse response=new ListLoanResponse();
        //soap ui used in dao part
        List<bank.project.dao.LoanScheme> loanSchemeList = bankService.listALLAvailableLoan();// pojo objects
        logger.info(" Will retrieve list of loan schemes");
        logger.info(loanSchemeList.toString());
        //created using xsd
        List<soap.project.bank.LoanScheme> loansList=new ArrayList<>();// xml list of objects as of now its empty

        //to itearate all data  one by one
        Iterator<bank.project.dao.LoanScheme> it= loanSchemeList.iterator();
        while(it.hasNext()){
            soap.project.bank.LoanScheme loan = new soap.project.bank.LoanScheme();// XSD POJO-create object for soap

            BeanUtils.copyProperties(it.next(),loan);
            loansList.add(loan);
        }
        //response object created in xsd
        response.getLoan().addAll(loansList);
        logger.info(response.toString());
        return response;
    }
}