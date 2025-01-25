package spring.ws.database.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.database.entity.CarEntity;
import spring.ws.database.entity.SellerEntity;

import java.util.List;
import java.util.Optional;


@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findAllBySellerPhoneNumber(Long phoneNumber);
    void deleteByAutoNumber(String autoNumber);
    Optional<CarEntity> findByAutoNumber(String autoNumber);


    @Modifying
    @Transactional
    @Query("UPDATE CarEntity c SET c.km = :km, c.carCondition = :carCondition, c.price = :price WHERE c.autoNumber = :autoNumber")
    void updateByAutoNumber(@Param("autoNumber") String autoNumber,
                            @Param("km") String km,
                            @Param("carCondition") String carCondition,
                            @Param("price") Long price);

}
