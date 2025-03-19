package spring.ws.http.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.ws.database.dto.read.BuyerReadDto;
import spring.ws.database.dto.read.SellerReadDto;
import spring.ws.database.repository.BuyerRepository;
import spring.ws.database.repository.SellerRepository;
import spring.ws.database.service.BuyerService;
import spring.ws.database.service.SellerService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/authorization")
public class AuthorizationController {
    private final BuyerService buyerService;
    private final BuyerRepository buyerRepository;
    private final SellerService sellerService;
    private final SellerRepository sellerRepository;

    //Todo VALID
    @PostMapping("/buyer/register")
    public String save(@ModelAttribute @Validated BuyerReadDto buyerReadDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("user", buyerReadDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/redirect/login";
        }
        buyerReadDto.setRole("buyer");
        buyerService.save(buyerReadDto);

        var user = buyerRepository.findById(Long.valueOf(buyerReadDto.getPhoneNumber()));
        return "redirect:/redirect/login";
    }

    //Todo VALID
    @PostMapping("/seller/register")
    public String save(@ModelAttribute @Validated SellerReadDto sellerReadDto,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes){
        System.out.println("method save active");
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("user", sellerReadDto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/authorization/seller/register";
        }
        sellerReadDto.setRole("seller");
        sellerService.save(sellerReadDto);

        var user = sellerRepository.findById(Long.valueOf(sellerReadDto.getPhoneNumber()));
        return "redirect:/redirect/login";
    }
}
