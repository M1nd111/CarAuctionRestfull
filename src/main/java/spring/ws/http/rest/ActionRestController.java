package spring.ws.http.rest;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.ws.database.dto.read.AuctionReadDto;
import spring.ws.database.dto.read.BuyerReadDto;
import spring.ws.database.dto.read.CarReadDto;
import spring.ws.database.dto.read.SellerReadDto;
import spring.ws.database.dto.write.Auction;
import spring.ws.database.entity.SellerEntity;
import spring.ws.database.repository.BuyerRepository;
import spring.ws.database.repository.CarRepository;
import spring.ws.database.repository.SellerRepository;
import spring.ws.database.service.AuctionService;
import spring.ws.database.service.BuyerService;
import spring.ws.database.service.CarService;
import spring.ws.database.service.SellerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/action")
@SessionAttributes({"user", "role", "phone"})
@RequiredArgsConstructor
public class ActionRestController {
    private final BuyerRepository buyerRepository;
    private final BuyerService buyerService;
    private final SellerRepository sellerRepository;
    private final SellerService sellerService;
    private final CarRepository carRepository;
    private final CarService carService;
    private final AuctionService auctionService;

    @GetMapping("/all")
    public List<CarReadDto> getAllCars(HttpSession session) {
        Long phone = Long.valueOf((String) session.getAttribute("phone"));
        return carService.findAllByPhone(phone);
    }

    @GetMapping("/allAuction")
    public List<Auction> getAllAuction(HttpSession session) {

        List<AuctionReadDto> auctionReadDtoList = auctionService.findAll();
        List<CarReadDto> carReadDtoList = new ArrayList<>();
        List<Auction> auctionList = new ArrayList<>();
        for (AuctionReadDto it : auctionReadDtoList){
            carReadDtoList.add(carService.findByAutoNumber(it.getAutoNumber()));
        }

        for (int i = 0; i < auctionReadDtoList.size(); i++) {
            AuctionReadDto auctionReadDto = auctionReadDtoList.get(i);
            CarReadDto carReadDto = carReadDtoList.get(i);

            Auction auction = Auction.builder()
                    .autoNumber(carReadDto.getAutoNumber())
                    .year(carReadDto.getYear())
                    .carCondition(carReadDto.getCarCondition())
                    .markAndModelName(carReadDto.getMarkAndModelName())
                    .km(carReadDto.getKm())
                    .price(carReadDto.getPrice())
                    .date(auctionReadDto.getDate())
                    .time(auctionReadDto.getTime())
                    .build();

            auctionList.add(auction);
        }


        return auctionList;
    }

    @PostMapping("/edit")
    public String edit(@RequestParam String autoNumber,
                       @RequestParam String km,
                       @RequestParam String carCondition,
                       @RequestParam String price,
                       HttpSession session){

        CarReadDto carReadDto =  carService.findByAutoNumber(autoNumber);
        carReadDto.setCarCondition(carCondition);
        carReadDto.setKm(km);
        carReadDto.setPrice(price);
        carService.edit(carReadDto);

        return "{\"status\":\"success\"}";
    }

    @PostMapping("/dell")
    public String dell(@RequestParam String autoNumber,
                      HttpSession session){
        carService.dellByAutoNumber(autoNumber);
        return "{\"status\":\"success\"}";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute CarReadDto carReadDto,
                      HttpSession session){
        Long phone = Long.valueOf((String) session.getAttribute("phone"));
        SellerEntity seller = sellerRepository.findById(phone).get();
        carService.save(carReadDto, seller);
        return "{\"status\":\"success\"}";
    }
    @PostMapping("/dellProfile")
    public ResponseEntity<Resource> redirectDellProfile(Model model, HttpSession httpSession,
                                                        HttpServletRequest request, HttpServletResponse response){
        String role = (String) httpSession.getAttribute("role");

        if(Objects.equals(role, "buyer")){
            BuyerReadDto buyerReadDto = (BuyerReadDto) httpSession.getAttribute("user");
            buyerService.dellUserByID(buyerReadDto.getPhoneNumber());
        }
        else if (Objects.equals(role, "seller")){
            SellerReadDto sellerReadDto = (SellerReadDto) httpSession.getAttribute("user");
            sellerService.dellUserByID(sellerReadDto.getPhoneNumber());
        }

        httpSession.invalidate();
        SecurityContextHolder.clearContext();

        new SecurityContextLogoutHandler().logout(request, response, null);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            auth.setAuthenticated(false);
        }

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


}

