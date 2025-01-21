package spring.ws.database.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.ws.database.entity.SellerEntity;

@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
}
