package bank.project.app;

import bank.project.dao.BankService;
import bank.project.dao.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomerLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    BankService bankService;
    private Logger logger = LoggerFactory.getLogger(BankService.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Customer customer = (Customer) authentication.getPrincipal();

        ResourceBundle resourceBundle = ResourceBundle.getBundle("loan");
        if (customer.getCustomerstatus().equalsIgnoreCase("inactive")) {
//        logger.info(resourceBundle.toString());
            logger.info("  not successful");
            super.setDefaultTargetUrl("/logout");
//        if(customer.getAttempts()==0)
//            logger.info("deactivate");
        } else {
            bankService.setAttempts(customer.getCustomerid());
            super.setDefaultTargetUrl("/web/dash");

        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}


