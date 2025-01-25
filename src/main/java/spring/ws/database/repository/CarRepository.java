package spring.ws.database.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.ws.database.entity.CarEntity;
import spring.ws.database.entity.SellerEntity;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findAllBySellerPhoneNumber(Long phoneNumber);

}
