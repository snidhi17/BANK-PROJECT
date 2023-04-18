package bank.project.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class MvnController {
    @GetMapping("/log")
    public String loginPage()
    {
        return "login";
    }
    @GetMapping("/dash")
    public String dash(){
        return "dash";
    }
    @GetMapping("/emi")
    public String loancCalculator()
    {

        return "emicalculator";
    }
}
