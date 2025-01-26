package spring.ws.database.service;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.database.dto.read.CarReadDto;
import spring.ws.database.dto.read.SellerReadDto;
import spring.ws.database.entity.CarEntity;
import spring.ws.database.entity.SellerEntity;
import spring.ws.database.repository.CarRepository;
import spring.ws.database.role.Role;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ToString
public class CarService {
    private final CarRepository carRepository;

    @Transactional
    public Boolean dellByAutoNumber(String autoNumber){
        carRepository.deleteByAutoNumber(autoNumber);
        carRepository.flush();
        return true;
    }

    @Transactional
    public CarReadDto findByAutoNumber(String autoNumber){
        return carRepository.findByAutoNumber(autoNumber).map(CarEntity -> CarReadDto.builder()
                .markAndModelName(CarEntity.getMarkAndModelName())
                .autoNumber(String.valueOf(CarEntity.getAutoNumber()))
                .year(String.valueOf(CarEntity.getYear()))
                .km(CarEntity.getKm())
                .carCondition(CarEntity.getCarCondition())
                .price(String.valueOf(CarEntity.getPrice()))
                .sellerPhone(String.valueOf(CarEntity.getSeller().getPhoneNumber()))
                .build()).get();

    }

    @Transactional
    public Boolean edit(CarReadDto carReadDto){
        carRepository.updateByAutoNumber(carReadDto.getAutoNumber(), carReadDto.getKm(),
                carReadDto.getCarCondition(), Long.valueOf(carReadDto.getPrice()));

        carRepository.flush();
        return true;
    }

    @Transactional
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
