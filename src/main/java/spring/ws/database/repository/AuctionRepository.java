package spring.ws.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.ws.database.entity.AuctionEntity;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {
}
