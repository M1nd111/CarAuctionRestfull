package spring.ws.database.service;


import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.database.dto.read.BuyerReadDto;
import spring.ws.database.entity.BuyerEntity;
import spring.ws.database.repository.BuyerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@ToString
@RequiredArgsConstructor
public class BuyerService {
    private final BuyerRepository buyerRepository;


    public List<BuyerReadDto> findAll() {

        return buyerRepository.findAll().stream().map(BuyerEntity -> BuyerReadDto.builder()
                .phoneNumber(BuyerEntity.getPhoneNumber())
                .fio(BuyerEntity.getFio())
                .passportNumber(BuyerEntity.getPassportNumber())
                .email(BuyerEntity.getEmail()).build()).collect(Collectors.toList());
    }


    public Boolean save(BuyerReadDto buyerReadDto) {
        buyerRepository.save(BuyerEntity.builder()
                .phoneNumber(buyerReadDto.getPhoneNumber())
                .fio(buyerReadDto.getFio())
                .passportNumber(buyerReadDto.getPassportNumber())
                .email(buyerReadDto.getEmail())
                .build());
        buyerRepository.flush();
        return true;
    }
    public Boolean delAll() {
        buyerRepository.deleteAll();
        buyerRepository.flush();
        return true;
    }
    
}
