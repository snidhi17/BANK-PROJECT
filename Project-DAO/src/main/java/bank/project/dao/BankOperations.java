package bank.project.dao;

import java.util.List;
import java.util.Optional;

public interface BankOperations {
  //created an interface to implement these methods
    List<Customer>listCustomer();
    Customer getByUsername(String username);
    //LOAN OPERATIONS
    List<LoanScheme> listALLAvailableLoan();
    LoanScheme listLoanDetails(String loan_scheme_type);
    float loanROI(String  loan_scheme_type);

}
