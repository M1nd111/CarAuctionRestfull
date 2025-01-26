package spring.ws.database.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.database.dto.read.BuyerReadDto;
import spring.ws.database.dto.read.OrderReadDto;
import spring.ws.database.entity.BuyerEntity;
import spring.ws.database.entity.OrderEntity;
import spring.ws.database.repository.BuyerRepository;
import spring.ws.database.repository.OrderRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ToString
public class OrderService {
    private final OrderRepository orderRepository;
    private final BuyerService buyerService;
    private final BuyerRepository buyerRepository;

    @Transactional
    public void save(OrderReadDto orderReadDto){
        BuyerEntity buyerEntity = buyerRepository.findByPhoneNumber(orderReadDto.getBuyerPhone()).get();

        orderRepository.save(OrderEntity.builder()
                        .autoNumber(orderReadDto.getAutoNumber())
                        .date(orderReadDto.getDate())
                        .time(orderReadDto.getTime())
                        .initialBid(orderReadDto.getInitialBid())
                        .buyer(buyerEntity)
                        .sellerPhone(orderReadDto.getSellerPhone())
                        .build());
    }

    public List<OrderReadDto> findByAutoNumber(String autoNumber){

        return orderRepository.findAllByAutoNumber(autoNumber).stream().map(OrderEntity -> OrderReadDto.builder()
                .sellerPhone(OrderEntity.getSellerPhone())
                .buyerPhone(OrderEntity.getBuyer().getPhoneNumber())
                .autoNumber(OrderEntity.getAutoNumber())
                .initialBid(OrderEntity.getInitialBid())
                .time(OrderEntity.getTime())
                .date(OrderEntity.getDate())
                .build()).collect(Collectors.toList());
    }

}
