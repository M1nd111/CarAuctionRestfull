package spring.ws.database.service;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.database.dto.read.AuctionReadDto;
import spring.ws.database.entity.AuctionEntity;
import spring.ws.database.repository.AuctionRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@ToString
public class AuctionService {
    private final AuctionRepository auctionRepository;

    @Transactional
    public void save(AuctionReadDto auctionReadDto){

        AuctionEntity auctionEntity = AuctionEntity.builder()
                .autoNumber(auctionReadDto.getAutoNumber())
                .date(auctionReadDto.getDate())
                .time(auctionReadDto.getTime())
                .build();

        auctionRepository.save(auctionEntity);

    }

    public List<AuctionReadDto> findAll(){
        return auctionRepository.findAll().stream().map(AuctionEntity -> AuctionReadDto.builder()
                .autoNumber(AuctionEntity.getAutoNumber())
                .date(AuctionEntity.getDate())
                .time(AuctionEntity.getTime())
                .build()).collect(Collectors.toList());
    }

}
