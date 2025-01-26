package spring.ws.http.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.ws.database.dto.read.*;
import spring.ws.database.entity.BuyerEntity;
import spring.ws.database.entity.SellerEntity;
import spring.ws.database.repository.AuctionRepository;
import spring.ws.database.repository.BuyerRepository;
import spring.ws.database.repository.CarRepository;
import spring.ws.database.repository.SellerRepository;
import spring.ws.database.service.AuctionService;
import spring.ws.database.service.BuyerService;
import spring.ws.database.service.CarService;
import spring.ws.database.service.SellerService;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private final CarService carService;
    private final AuctionService auctionService;



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

    @GetMapping("/put")
    public String redirectPagePut(@RequestParam String autoNumber,
                                  @RequestParam LocalDate datePut,
                                  @RequestParam LocalTime timePut,
                                  Model model,
                                  HttpSession httpSession){
        model.addAttribute("date", datePut);
        model.addAttribute("time", timePut);

        CarReadDto carReadDto = carService.findByAutoNumber(autoNumber);
        model.addAttribute("car", carReadDto);

        AuctionReadDto auctionReadDto = AuctionReadDto.builder()
                .autoNumber(autoNumber)
                .date(datePut)
                .time(timePut)
                .build();
        auctionService.save(auctionReadDto);

        auctionReadDto = auctionService.findByAutoNumber(autoNumber);
        model.addAttribute("id", auctionReadDto.getId());

        String role = (String) httpSession.getAttribute("role");
        model.addAttribute("role", role);
        return "user/order";
    }

    @GetMapping("/participate")
    public String redirectPagePutP(@RequestParam String autoNumber,
                                   Model model, HttpSession httpSession){

        AuctionReadDto auctionReadDto = auctionService.findByAutoNumber(autoNumber);

        model.addAttribute("date", auctionReadDto.getDate());
        model.addAttribute("time", auctionReadDto.getTime());
        model.addAttribute("id", auctionReadDto.getId());

        CarReadDto carReadDto = carService.findByAutoNumber(autoNumber);
        model.addAttribute("car", carReadDto);

        String role = (String) httpSession.getAttribute("role");
        model.addAttribute("role", role);
        return "user/order";
    }

    @GetMapping("/bids")
    public String redirectPageBids(){

        return "user/auction";
    }


    @GetMapping("/sellerRegistration")
    public String getRegisterPageSeller(){
        return "user/sellerRegistration";
    }

    @GetMapping("/buyerRegistration")
    public String getRegisterPageBuyer(){
        return "user/buyerRegistration";
    }

}
