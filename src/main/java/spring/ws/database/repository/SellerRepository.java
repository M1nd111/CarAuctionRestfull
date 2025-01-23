package spring.ws.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.ws.database.entity.SellerEntity;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
    Optional<SellerEntity> findByEmail(String email);
}
