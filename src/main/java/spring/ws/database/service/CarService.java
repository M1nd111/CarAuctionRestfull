package spring.ws.database.service;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.ws.database.dto.read.CarReadDto;
import spring.ws.database.dto.read.SellerReadDto;
import spring.ws.database.entity.CarEntity;
import spring.ws.database.entity.SellerEntity;
import spring.ws.database.repository.CarRepository;
import spring.ws.database.role.Role;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ToString
public class CarService {
    private final CarRepository carRepository;

    public Boolean save(CarReadDto carReadDto, SellerEntity sellerEntity){
        carRepository.save(CarEntity.builder()
                .markAndModelName(carReadDto.getMarkAndModelName())
                .autoNumber(carReadDto.getAutoNumber())
                .year(Year.parse(carReadDto.getYear()))
                .km(carReadDto.getKm())
                .carCondition(carReadDto.getCarCondition())
                .price(Long.valueOf(carReadDto.getPrice()))
                .seller(sellerEntity)
                .build());
        carRepository.flush();
        return true;
    }

    public List<CarReadDto> findAllByPhone(Long phone) {

        return carRepository.findAllBySellerPhoneNumber(phone).stream().map(CarEntity -> CarReadDto.builder()
                .markAndModelName(CarEntity.getMarkAndModelName())
                .autoNumber(String.valueOf(CarEntity.getAutoNumber()))
                .year(String.valueOf(CarEntity.getYear()))
                .km(CarEntity.getKm())
                .carCondition(CarEntity.getCarCondition())
                .price(String.valueOf(CarEntity.getPrice()))
                .build()).collect(Collectors.toList());
    }
}
