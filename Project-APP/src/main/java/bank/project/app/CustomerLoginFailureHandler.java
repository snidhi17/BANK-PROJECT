package bank.project.app;

import bank.project.dao.BankService;
import bank.project.dao.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomerLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("loan");

    @Autowired
    private BankService bankService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String userName=request.getParameter("username");
        String passWord=request.getParameter("password");
        Customer customer=bankService.getByUsername(userName);
        if(customer==null){
            logger.info("User doesn't exists");
            exception=new LockedException(resourceBundle.getString("db_user"));
            super.setDefaultFailureUrl("/web/log?error="+ resourceBundle.getString("db_user"));

        }
        else{
            if(customer.getCustomerstatus().equalsIgnoreCase("inactive")){
                logger.info(resourceBundle.getString("accInactive"));
                exception=new LockedException(resourceBundle.getString("accInactive"));
                super.setDefaultFailureUrl("/web/log?error="+ resourceBundle.getString("accInactive"));
            }
            else{
                bankService.incrementFailedAttempts(customer.getCustomerid());
                int attempts=bankService.getAttempts(customer.getCustomerid());
                if(attempts==1){
                    logger.info(resourceBundle.getString("db_incorrect_pw")+resourceBundle.getString("attempt2"));
                    exception=new LockedException(resourceBundle.getString("attempt2")+resourceBundle.getString("db_incorrect_pw"));
                    super.setDefaultFailureUrl("/web/log?error="+ resourceBundle.getString("db_incorrect_pw")+resourceBundle.getString("attempt2"));
                }
                else if(attempts==2){
                    logger.info(resourceBundle.getString("db_incorrect_pw")+resourceBundle.getString("attempt1"));
                    exception=new LockedException(resourceBundle.getString("attempt1")+resourceBundle.getString("db_incorrect_pw"));
                    super.setDefaultFailureUrl("/web/log?error="+ resourceBundle.getString("db_incorrect_pw")+resourceBundle.getString("attempt1"));
                }
                else{
                    logger.info(resourceBundle.getString("db_unsuccessfull"));
                    exception=new LockedException(resourceBundle.getString("db_unsuccessfull"));
                    //3rd failure attempt account inactive
                    bankService.updateStatus();
                    super.setDefaultFailureUrl("/web/log?error=" + resourceBundle.getString("db_unsuccessfull"));
                }
            }
        }
        super.onAuthenticationFailure(request, response, exception);

    }
}