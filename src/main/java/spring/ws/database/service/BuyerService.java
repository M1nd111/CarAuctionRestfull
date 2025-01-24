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
import spring.ws.database.dto.read.BuyerReadDto;
import spring.ws.database.dto.read.SellerReadDto;
import spring.ws.database.entity.BuyerEntity;
import spring.ws.database.repository.BuyerRepository;
import spring.ws.database.repository.SellerRepository;
import spring.ws.database.role.Role;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@ToString
@RequiredArgsConstructor
public class BuyerService implements UserDetailsService{
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    public void dellUserByID(String id){
        buyerRepository.deleteById(Long.valueOf(id));
    }

    public BuyerReadDto findByEmail(String email) {

        return buyerRepository.findByEmail(email).map(BuyerEntity -> BuyerReadDto.builder()
                .phoneNumber(String.valueOf(BuyerEntity.getPhoneNumber()))
                .fio(BuyerEntity.getFio())
                .passportNumber(String.valueOf(BuyerEntity.getPassportNumber()))
                .email(BuyerEntity.getEmail())
                .password(BuyerEntity.getPassword())
                .role(String.valueOf(BuyerEntity.getRole()))
                .build()).get();
    }

    public List<BuyerReadDto> findAll() {

        return buyerRepository.findAll().stream().map(BuyerEntity -> BuyerReadDto.builder()
                .phoneNumber(String.valueOf(BuyerEntity.getPhoneNumber()))
                .fio(BuyerEntity.getFio())
                .passportNumber(String.valueOf(BuyerEntity.getPassportNumber()))
                .email(BuyerEntity.getEmail())
                .password(BuyerEntity.getPassword())
                .role(String.valueOf(BuyerEntity.getRole()))
                .build()).collect(Collectors.toList());
    }


    public Boolean save(BuyerReadDto buyerReadDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        buyerRepository.save(BuyerEntity.builder()
                .phoneNumber(Long.valueOf(buyerReadDto.getPhoneNumber()))
                .fio(buyerReadDto.getFio())
                .passportNumber(Long.valueOf(buyerReadDto.getPassportNumber()))
                .email(buyerReadDto.getEmail())
                .password(passwordEncoder.encode(buyerReadDto.getPassword()))
                .role(Role.valueOf(buyerReadDto.getRole()))
                .build());
        buyerRepository.flush();
        return true;
    }
    public Boolean delAll() {
        buyerRepository.deleteAll();
        buyerRepository.flush();
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("\n\nloadUserByUsername active\n\n");

        return sellerRepository.findByEmail(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .or(() -> buyerRepository.findByEmail(username).map(user ->
                        new org.springframework.security.core.userdetails.User(
                                user.getEmail(),
                                user.getPassword(),
                                Collections.singleton(user.getRole())
                        )
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to find user: " + username));
    }
}
