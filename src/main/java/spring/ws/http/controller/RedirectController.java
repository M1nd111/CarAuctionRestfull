package spring.ws.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class RedirectController {

    @GetMapping
    public String redirectPage(){
        return "user/authorization";
    }

}
