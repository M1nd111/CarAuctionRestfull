package spring.ws.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @GetMapping("/login")
    public String redirectPage(){
        return "user/redirect";
    }

    @GetMapping("/buyerAuthorization")
    public String getAuthorizationPageBuyer(){
        return "user/buyerAuthorization";
    }

    @GetMapping("/sellerAuthorization")
    public String getAuthorizationPageSeller(){
        return "user/sellerAuthorization";
    }

    @GetMapping("/sellerRegistration")
    public String getRegisterPageBuyer(){
        return "user/buyerRegistration";
    }

    @GetMapping("/buyerRegistration")
    public String getRegisterPageSeller(){
        return "user/sellerRegistration";
    }

}
