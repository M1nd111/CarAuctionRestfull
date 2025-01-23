package spring.ws.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import spring.ws.database.dto.read.LoginDto;
import spring.ws.database.dto.read.SellerReadDto;
import spring.ws.database.repository.BuyerRepository;
import spring.ws.database.repository.SellerRepository;

@Controller
@RequestMapping("/redirect")
@RequiredArgsConstructor
public class RedirectController {
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    @GetMapping("/login")
    public String redirectPage(){
        return "user/redirect";
    }
    @PostMapping("/login")
    public void saveUserLoginPvd(@RequestBody LoginDto loginDto){
        System.out.println(loginDto.toString());
    }
    @GetMapping("/main")
    public String redirectPageMain(){
//        sellerRepository.findByEmail(loginDto.getUsername()).get();
//        buyerRepository.findByEmail(loginDto.getUsername()).get();
        return "user/auction";
    }
    @GetMapping("/profile")
    public String redirectPageProfile(){

        return "user/profile";
    }
    @GetMapping("/bids")
    public String redirectPageBids(){

        return "user/auction";
    }

//    @PostMapping("/login")
//    public String login(Model model, @ModelAttribute("login") LoginDto loginDto){
//        System.out.println(loginDto.toString());
//        return "user/main";
//    }


    @GetMapping("/sellerRegistration")
    public String getRegisterPageSeller(){
        return "user/sellerRegistration";
    }

    @GetMapping("/buyerRegistration")
    public String getRegisterPageBuyer(){
        return "user/buyerRegistration";
    }

}
