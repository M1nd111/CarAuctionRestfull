package spring.ws.database.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderNumber;

    @Column(name = "auto_number")
    private String autoNumber;
    @Column(name = "seller_phone")
    private Long sellerPhone;

    @Column(name = "initial_bid")
    private String initialBid;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "buyer_phone")
    private BuyerEntity buyer;

    @Override
    public void setId(Long id) {

    }

    @Override
    public Long getId() {
        return null;
    }
}
