package spring.ws.database.entity;

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
    private Long orderNumber;
    private Long autoNumber;
    private Long sellerPhone;
    private Long buyerPhone;
    private String orderNow;
    private LocalDate date;
    private LocalTime time;

    @Override
    public void setId(Long id) {

    }

    @Override
    public Long getId() {
        return null;
    }
}
