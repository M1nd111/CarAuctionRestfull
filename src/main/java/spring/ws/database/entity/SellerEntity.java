package spring.ws.database.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.ws.database.role.Role;

import java.util.ArrayList;
import java.util.List;

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
    private Long inn;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarEntity> cars = new ArrayList<>();
    @Override
    public void setId(Long id) {
        this.phoneNumber = id;
    }

    @Override
    public Long getId() {
        return null;
    }

}
