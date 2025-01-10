package spring.ws.http.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sellerAuthorization")
public class SellerAuthController {

    @GetMapping
    public String getRegisterPage(){
        return "user/sellerAuthorization";
    }
}
