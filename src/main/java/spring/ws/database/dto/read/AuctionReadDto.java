package spring.ws.database.dto.read;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class AuctionReadDto {
    private String autoNumber;
    private LocalDate date;
    private LocalTime time;
}
