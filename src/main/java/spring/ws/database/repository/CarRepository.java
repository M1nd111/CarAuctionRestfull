package spring.ws.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.ws.database.entity.CarEntity;


@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
