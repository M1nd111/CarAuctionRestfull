package spring.ws.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auction")
public class AuctionEntity implements BaseEntity{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "auto_number")
    private String autoNumber;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private LocalTime time;

    @Override
    public void setId(Serializable id) {

    }

    @Override
    public Serializable getId() {
        return null;
    }

}
