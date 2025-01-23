package spring.ws.database.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.database.dto.read.SellerReadDto;
import spring.ws.database.entity.BuyerEntity;
import spring.ws.database.entity.SellerEntity;
import spring.ws.database.repository.SellerRepository;
import spring.ws.database.role.Role;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ToString
@Transactional
public class SellerService {
    private final SellerRepository sellerRepository;


    public List<SellerReadDto> findAll() {

        return sellerRepository.findAll().stream().map(SellerEntity -> SellerReadDto.builder()
                .phoneNumber(String.valueOf(SellerEntity.getPhoneNumber()))
                .fio(SellerEntity.getFio())
                .inn(String.valueOf(SellerEntity.getInn()))
                .email(SellerEntity.getEmail())
                .password(SellerEntity.getPassword())
                .role(String.valueOf(SellerEntity.getRole()))
                .build()).collect(Collectors.toList());
    }


    public Boolean save(SellerReadDto sellerReadDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sellerRepository.save(SellerEntity.builder()
                .phoneNumber(Long.valueOf(sellerReadDto.getPhoneNumber()))
                .fio(sellerReadDto.getFio())
                .inn(Long.valueOf(sellerReadDto.getInn()))
                .email(sellerReadDto.getEmail())
                .password(passwordEncoder.encode(sellerReadDto.getPassword()))
                .role(Role.valueOf(sellerReadDto.getRole()))
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
