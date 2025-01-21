package spring.ws.database.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "seller")
public class SellerEntity implements BaseEntity<Long>{


    @Id
    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "fio")
    private String fio;

    @Column(name = "inn")
    private Integer inn;

    @Column(name = "email")
    private String email;

    @Override
    public void setId(Long id) {
        this.phoneNumber = id;
    }

    @Override
    public Long getId() {
        return null;
    }

}
