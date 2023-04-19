package bank.project.dao;



import bank.project.dao.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DaoApplicationTests {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    BankService bankService;

    @Test
    public void testListAllCustomer() {
        Customer customer1 = new Customer(1, "7477 ", "Manvith", "Udupi", "Inactive", "manvith", 878773435, 3);
        Customer customer2 = new Customer(2, "3432", "Nidhi", "Karkala", "Active", "nidhi", 776567785, 2);
        List<Customer> tempList = Stream.of(customer1, customer2).collect(Collectors.toList());
        when(jdbcTemplate.query(eq("select * from customer"), any(RowMapper.class))).thenReturn(tempList);
        assertEquals(customer2, bankService.listCustomer().get(1));

    }

    @Test
    public void testListAllLoans() {
        LoanScheme loanScheme1 = new LoanScheme(4, "personal loan", "Assured personal loan", "taken for any personal problems", 0.09f);
        LoanScheme loanScheme2 = new LoanScheme(6, "gold loan", "bhima gold", "to buy gold at less rate of interest", 0.30f);
        LoanScheme loanScheme3 = new LoanScheme(2, "vehicle loan", "IOR loans", "gives good value", 0.44f);
        List<LoanScheme> tempList = Stream.of(loanScheme1, loanScheme2, loanScheme3).collect(Collectors.toList());
        when(jdbcTemplate.query(eq("select * from customer"), any(RowMapper.class))).thenReturn(tempList);
        assertEquals(loanScheme3, bankService.listCustomer().get(2));


    }
    @Test
    public void testGetUsername(){

        Customer customer1 = new Customer(1, "7477 ", "Manvith", "Udupi", "Inactive", "manvith", 878773435, 3);
        String username="manvith";
        when(jdbcTemplate.queryForObject(eq("select * from CUSTOMER where USERNAME=?"),  any(RowMapper.class),eq(username)))
               .thenReturn(customer1);
        Customer customer2=bankService.getByUsername("manvith");
        assertEquals(customer1.getUsername(),customer1.getUsername());

    }

    @Test
    public void testlistloanDetails(){
        LoanScheme loanScheme3 = new LoanScheme(4, "personal loan", "Assured personal loan", "taken for any personal problems", 0.09f);
        LoanScheme loanScheme4 = new LoanScheme(2, "vehicle loan", "IOR loans", "gives good value", 0.44f);
        List<LoanScheme> tempList = Stream.of(loanScheme3, loanScheme4 ).collect(Collectors.toList());
        String schemeType="Personal Loan";
        when(jdbcTemplate.queryForObject(eq("select loan_scheme_desc,loan_scheme_roi,loan_scheme_name,loan_scheme_type from loanscheme where loan_scheme_type=?"), any(RowMapper.class),eq(schemeType))).thenReturn(loanScheme3);
        assertEquals(loanScheme3, bankService.listLoanDetails(schemeType));
    }

    @Test
    public void testloanRoi(){
        LoanScheme loanScheme3= new LoanScheme(4, "personal loan", "Assured personal loan", "taken for any personal problems", 0.09f);
        String schemeType="Personal Loan";
        when(jdbcTemplate.queryForObject(eq("select loan_scheme_roi from loanscheme where loan_scheme_type=?"),eq(Float.class),eq(schemeType))).thenReturn(5.7f);
        assertEquals(5.7f,bankService.loanROI("Personal Loan"));
    }
}



