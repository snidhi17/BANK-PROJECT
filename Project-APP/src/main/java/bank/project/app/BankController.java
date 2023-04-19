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

//    @GetMapping("/")
//    public List<Customer> callList() {
//        logger.info("ControlleraboutprintAlltherecords");
//        return bankService.listAll();
//    }



//    @PostMapping("/byname")
//    public String callReadByName(@RequestParam("username") String username, @RequestParam("password") String password){
//       // logger.warn("Through the name of "+name+" controller trying to find the records");
//        return "username";

//    @GetMapping("/gets")
//    public List<LoanScheme> callList() {
//        return bankService.listloanAll();
//    }


    @GetMapping("/gets")       //to take value VIA ajax
    public List<LoanScheme> callListOfLoan() {
        List<LoanScheme> listLoan=bankService.listALLAvailableLoan();//create object
        logger.info("List of loans");
        return listLoan;              //return object

    }

    @GetMapping("/details/{loan_scheme_type}")
    //service method listloanDetails called here
    public LoanScheme listLoanDetails(@PathVariable("loan_scheme_type") String loan_scheme_type){
        logger.info("Controller will find records that matches "+loan_scheme_type);
        return bankService.listLoanDetails(loan_scheme_type);


    }

    @GetMapping("/scheme/{loanScheme}")
    public float call(@PathVariable("loanScheme") String loan_scheme_type){
        //service method to return rate of interest called here
        logger.debug("Controller about to find record matches with "+loan_scheme_type);
        return bankService.loanROI(loan_scheme_type);
    }


    @PostMapping("/authentication")
    //method for authenticating username and password
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        logger.info("Enter the username and password");
        Customer customer = bankService.getByUsername(username);
        if (customer == null){
            //if customer doesn't exist message in the property fill will be displayed
            logger.info(resourceBundle.getString("db_user"));
            return resourceBundle.getString("db_user");
        }
        else {
            logger.info(customer.getCustomerstatus());
            //first it will retrieve customer status and if status is inactive appropiate message is displayed
            if (customer.getCustomerstatus().equalsIgnoreCase("Inactive")){
                logger.info(resourceBundle.getString("db_unsuccessfull"));
                return resourceBundle.getString("db_unsuccessfull");
            }
            if (!password.equals(customer.getPassword())) {
                //password doesn't match then account is deactivated after three attempts
                bankService.incrementFailedAttempts(customer.getCustomerid());
                logger.info(resourceBundle.getString("db_incorrect_pw"));
                return resourceBundle.getString("db_incorrect_pw");
            } else
                //if success
                logger.info(resourceBundle.getString("db_success"));
            return resourceBundle.getString("db_success");

        }
    }
}




