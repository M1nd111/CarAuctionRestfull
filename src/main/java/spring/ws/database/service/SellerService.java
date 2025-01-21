package spring.ws.database.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.database.dto.read.SellerReadDto;
import spring.ws.database.entity.BuyerEntity;
import spring.ws.database.entity.SellerEntity;
import spring.ws.database.repository.SellerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ToString
@Transactional
public class SellerService {
    private final SellerRepository sellerRepository;
    
    
    public List<SellerReadDto> findAll() {

        return sellerRepository.findAll().stream().map(BuyerEntity -> SellerReadDto.builder()
                .phoneNumber(BuyerEntity.getPhoneNumber())
                .fio(BuyerEntity.getFio())
                .inn(BuyerEntity.getInn())
                .email(BuyerEntity.getEmail()).build()).collect(Collectors.toList());
    }


    public Boolean save(SellerReadDto sellerReadDto) {
        sellerRepository.save(SellerEntity.builder()
                .phoneNumber(sellerReadDto.getPhoneNumber())
                .fio(sellerReadDto.getFio())
                .inn(sellerReadDto.getInn())
                .email(sellerReadDto.getEmail())
                .build());
        sellerRepository.flush();
        return true;
    }
    public Boolean delAll() {
        sellerRepository.deleteAll();
        sellerRepository.flush();
        return true;
    }
}
