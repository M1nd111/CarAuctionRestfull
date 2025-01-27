package spring.ws.database.repository;


import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.database.entity.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllByAutoNumber(String autoNumber);
    @Query("SELECT o FROM OrderEntity o " +
            "WHERE o.autoNumber = :autoNumber " +
            "ORDER BY CAST(o.initialBid AS int) DESC")
    List<OrderEntity> findTopByAutoNumberOrderByInitialBidDesc(@Param("autoNumber") String autoNumber);
    @Query("SELECT o FROM OrderEntity o " +
            "WHERE o.buyer.phoneNumber = :buyerPhone AND o.status = true " +
            "ORDER BY CAST(o.initialBid AS int) DESC")
    List<OrderEntity> findTopByBuyerPhoneAndStatusOrderByInitialBidDesc(@Param("buyerPhone") String buyerPhone);

    @Query("SELECT o FROM OrderEntity o " +
            "WHERE o.sellerPhone = :sellerPhone AND o.status = true " +
            "ORDER BY CAST(o.initialBid AS int) DESC")
    List<OrderEntity> findTopBySellerPhoneAndStatusOrderByInitialBidDesc(@Param("sellerPhone") String sellerPhone);
    @Modifying
    @Transactional
    @Query("UPDATE OrderEntity o SET " +
            "o.status = :#{#orderEntity.status} " +
            "WHERE o.orderNumber = :orderNumber")
    int updateByOrderNumber(@Param("orderNumber") Long orderNumber, @Param("orderEntity") OrderEntity orderEntity);

    OrderEntity findByOrderNumber(Long orderNumber);
}


