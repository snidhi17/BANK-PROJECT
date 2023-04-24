package bank.project.app;


import bank.project.dao.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(SpringRunner.class)
@SpringBootTest
class AppApplicationTests{
    @Mock
    BankService bankService;

    @InjectMocks
    BankController bankController;

//Test case for retrieving ROI for loan
    @Test
    public void testApplyLoan(){
        Mockito.when(bankService.loanROI("personal loan")).thenReturn(5.7f);
        float getLoanROIResponse=bankController.listLoanROI("personal loan");
        assertEquals(getLoanROIResponse,5.7f);
    }
}
