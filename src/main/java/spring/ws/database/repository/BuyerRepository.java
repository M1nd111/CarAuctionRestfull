package spring.ws.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.ws.database.entity.BuyerEntity;
import spring.ws.database.entity.SellerEntity;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerEntity, Long> {
    Optional<BuyerEntity> findByEmail(String email);
    Optional<BuyerEntity> findByPhoneNumber(Long phone);
}
