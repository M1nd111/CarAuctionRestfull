package spring.ws.database.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long autoNumber;
    private String markAndModelName;
    private Year year;
    private String km;
    private String feel;
    private Long price;
    @Override
    public void setId(Long id) {

    }

    @Override
    public Long getId() {
        return null;
    }
}
