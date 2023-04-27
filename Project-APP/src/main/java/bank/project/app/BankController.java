package bank.project.app;


import bank.project.dao.BankService;
import bank.project.dao.Customer;
import bank.project.dao.LoanScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ResourceBundle;

@ComponentScan("bank.project.dao")//name of package in pom
@RestController
@RequestMapping("/loan")
public class BankController {

    @Autowired
    private BankService bankService;
    private Logger logger = LoggerFactory.getLogger(BankService.class);//to create loggers
    ResourceBundle resourceBundle = ResourceBundle.getBundle("loan");//to create resource bundle for property file



    @GetMapping("/scheme/{loanScheme}")
    public float listLoanROI(@PathVariable("loanScheme") String loan_scheme_type){
        //service method to return rate of interest called here
        logger.debug("Controller about to find record matches with "+loan_scheme_type);
        return bankService.loanROI(loan_scheme_type);
    }
}




