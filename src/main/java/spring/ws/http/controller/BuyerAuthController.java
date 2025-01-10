package spring.ws.http.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buyerAuthorization")
public class BuyerAuthController {

    @GetMapping
    public String getRegisterPage(){
        return "user/buyerAuthorization";
    }
}
