package bank.project.dao;

import java.util.List;
import java.util.Optional;

public interface BankOperations {
    List<Customer>listCustomer();
   // List<Customer> readUserName(String name);
    Customer getByUsername(String username);

    void decrementAttempts(int id);

    void setAttempts(int id);

    void updateStatus();

    void incrementFailedAttempts(int id);
    List<LoanScheme> listloanAll();
    //LoanScheme getLoanDetails(int loan_scheme_id);
    LoanScheme listLoanDetails(String loan_scheme_type);
    float loanROI(String  loan_scheme_type);

}
