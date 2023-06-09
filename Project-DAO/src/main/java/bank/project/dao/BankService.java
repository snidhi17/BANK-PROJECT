package bank.project.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

@Service
public class BankService implements  BankOperations, UserDetailsService {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger(BankService.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("loan");


    public List<Customer> listCustomer()
    {
        //service method to list all the details of  the customer
        logger.info("It will return all the customer details");
        return jdbcTemplate.query("select * from customer", new CustomerMapper());
    }


    @Override
    public Customer getByUsername(String username) {
        //service method to get all detail of customer by using username
        try {
            Customer customer = jdbcTemplate.queryForObject("select * from CUSTOMER where USERNAME=?", new CustomerMapper(), username);
            logger.info("will retrieve data from table based on "+username);
            return customer;
        } catch (DataAccessException e) {
            logger.info("Exception");
            return null;
        }
    }
    public int getAttempts(int id) {
        int attempts = jdbcTemplate.queryForObject("select FAILED_ATTEMPTS from CUSTOMER where CUSTOMER_ID=?",Integer.class,id);
        logger.info("Returned Attempts");
        return attempts;
    }


    public void setAttempts(int id) {
        jdbcTemplate.update("update CUSTOMER set FAILED_ATTEMPTS=0 where CUSTOMER_ID=?",id);
        logger.info("Set attempts to 3");
    }

    //to set sttatus to inactive if attempt is 3
     public void updateStatus() {
        jdbcTemplate.update("update CUSTOMER set CUSTOMER_STATUS='Inactive' where FAILED_ATTEMPTS=3");
        logger.info("Status set to inactive");
    }

    public void incrementFailedAttempts(int id) {
        //if three unsucessfull attempts customer account will be deactivated
        jdbcTemplate.update("update CUSTOMER set FAILED_ATTEMPTS = FAILED_ATTEMPTS + 1 where CUSTOMER_ID=?", id);
        jdbcTemplate.update("update CUSTOMER set CUSTOMER_STATUS='Inactive' where FAILED_ATTEMPTS=3");
    }

    //LOAN OPERATIONS
    @Override
    public List<LoanScheme> listALLAvailableLoan() {
        //to list all the details from loan scheme table
        logger.info("Gives details regarding the loan scheme");
        return jdbcTemplate.query("select * from loanscheme", new LoanMapper());
    }

    public LoanScheme listLoanDetails(String loan_scheme_type){
        //to retrieve details like loan description,loan name,loan roi from loan scheme table by using loan type
        logger.info("gives details from loan scheme table based on "+loan_scheme_type);
        return (jdbcTemplate.queryForObject("select loan_scheme_desc,loan_scheme_roi,loan_scheme_name,loan_scheme_type from loanscheme where loan_scheme_type=?",new LoanMapper(),loan_scheme_type));
    }

    @Override

    public float loanROI(String loan_scheme_type) {
        //to retrieve loan roi which is of type float and parameter is loan_scheme_type
        logger.info("Rate of interest calculated");
        return jdbcTemplate.queryForObject("select loan_scheme_roi from loanscheme where loan_scheme_type=?",Float.class,loan_scheme_type);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Entered loadByUsername() method"+username);
        Customer customer = getByUsername(username);

        if (customer == null){
            throw new UsernameNotFoundException(resourceBundle.getString("db_user"));
        }
        logger.info("Leaving loadByUsername() method"+username);
        if (customer.getCustomerstatus().equalsIgnoreCase("inactive")){
            throw new UsernameNotFoundException(resourceBundle.getString("accInactive"));
        }
        return customer;
    }


    //created mapper class for bothcustomer and loan table
    class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

            Customer customer = new Customer();
            customer.setCustomercontact(rs.getLong("customer_contact"));
            customer.setCustomerid(rs.getInt("customer_id"));
            customer.setUsername(rs.getString("username"));
            customer.setPassword(rs.getString("password"));
            customer.setCustomername(rs.getString("customer_name"));
            customer.setCustomeraddress(rs.getString("customer_address"));
            customer.setCustomerstatus(rs.getString("customer_status"));
            customer.setUsername(rs.getString("username"));
            customer.setFailedattempts(rs.getInt("failed_attempts"));
            return customer;
        }
    }
    class LoanMapper implements RowMapper<LoanScheme> {
        @Override
        public LoanScheme mapRow(ResultSet rs, int rowNum) throws SQLException {
            LoanScheme loanScheme= new LoanScheme();
            loanScheme.setLoanSchemeRoi(rs.getFloat("loan_scheme_roi"));
            loanScheme.setLoanSchemeId(rs.getInt("loan_scheme_id"));
            loanScheme.setLoanSchemeDesc(rs.getString("loan_scheme_desc"));
            loanScheme.setLoanSchemeName(rs.getString("loan_scheme_name"));
            loanScheme.setLoanSchemeType(rs.getString("loan_scheme_type"));
            return loanScheme;
        }
    }

}

