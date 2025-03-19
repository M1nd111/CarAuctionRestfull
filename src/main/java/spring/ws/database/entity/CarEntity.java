package spring.ws.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cars")
public class CarEntity implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "auto_number", unique = true)
    private String autoNumber;
    @Column(name = "mark_and_model_name")
    private String markAndModelName;
    @Column(name = "year")
    private Year year;
    @Column(name = "km")
    private String km;
    @Column(name = "car_condition")
    private String carCondition;
    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "seller_phone")
    private SellerEntity seller;
    @Override
    public void setId(Long id) {

    }

    @Override
    public Long getId() {
        return null;
    }
}
