package spring.ws.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class OrderEntity implements BaseEntity<Long> {

    @Id
    @Column(name = "order_number")
    private Long orderNumber;

    @Column(name = "auto_number")
    private Long autoNumber;
    @Column(name = "seller_phone")
    private Long sellerPhone;
    @Column(name = "buyer_phone")
    private Long buyerPhone;
    @Column(name = "initial_bid")
    private String initialBid;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private LocalTime time;

    @Override
    public void setId(Long id) {

    }

    @Override
    public Long getId() {
        return null;
    }
}
