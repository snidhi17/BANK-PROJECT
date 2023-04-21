package bank.project.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class MvnController {

    @GetMapping("/log")
    //direct to login page
    public String loginPage()
    {
        return "login";
    }
    @GetMapping("/dash")
    //direct to dash page
    public String dash()
    {
        return "dash";
    }
    @GetMapping("/emi")
    //direct to page with emi calculator
    public String loanCalculator()
    {
        return "emicalculator";
    }
}
