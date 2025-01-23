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
@Table(name = "buyer")
public class BuyerEntity implements BaseEntity<Long>{

    @Id
    @Column(name = "phone_number")
    private Long phoneNumber;

    @Column(name = "fio")
    private String fio;

    @Column(name = "passport_number")
    private Long passportNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Builder.Default
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();

    @Override
    public void setId(Long id) {
        this.phoneNumber = id;
    }

    @Override
    public Long getId() {
        return null;
    }
}
