package spring.ws.http.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.ws.database.dto.read.BuyerReadDto;
import spring.ws.database.dto.read.LoginDto;
import spring.ws.database.dto.read.SellerReadDto;
import spring.ws.database.entity.BuyerEntity;
import spring.ws.database.entity.SellerEntity;
import spring.ws.database.repository.BuyerRepository;
import spring.ws.database.repository.SellerRepository;
import spring.ws.database.service.BuyerService;
import spring.ws.database.service.SellerService;

import java.util.Objects;

@Controller
@RequestMapping("/redirect")
@RequiredArgsConstructor
@SessionAttributes({"user", "role", "phone"})
public class RedirectController {
    private final BuyerRepository buyerRepository;
    private final BuyerService buyerService;
    private final SellerRepository sellerRepository;
    private final SellerService sellerService;



    @GetMapping("/login")
    public String redirectPage(){
        return "user/redirect";
    }
    @GetMapping("/main")
    public String redirectPageMain(Model model, HttpSession httpSession){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        System.out.println(username);

        if(sellerRepository.findByEmail(username).isPresent()){
            SellerReadDto sellerReadDto = sellerService.findByEmail(username);
            model.addAttribute("role", sellerReadDto.getRole());
            httpSession.setAttribute("user", sellerReadDto);
            httpSession.setAttribute("role", sellerReadDto.getRole());
            httpSession.setAttribute("phone", sellerReadDto.getPhoneNumber());
        } else if (buyerRepository.findByEmail(username).isPresent()){
            BuyerReadDto buyerReadDto = buyerService.findByEmail(username);
            model.addAttribute("role", buyerReadDto.getRole());
            httpSession.setAttribute("user", buyerReadDto);
            httpSession.setAttribute("role", buyerReadDto.getRole());
            httpSession.setAttribute("phone", buyerReadDto.getPhoneNumber());
        } else {
            System.out.printf("ERROR: USER %s NOT FOUND", username);
        }

        return "user/auction";
    }
    @GetMapping("/profile")
    public String redirectPageProfile(Model model, HttpSession httpSession){
        model.addAttribute("role", httpSession.getAttribute("role"));
        model.addAttribute("user", httpSession.getAttribute("user"));
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
