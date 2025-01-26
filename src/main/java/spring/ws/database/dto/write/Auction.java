package spring.ws.database.dto.write;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class Auction {
    private String autoNumber;
    private String markAndModelName;
    private String year;
    private String km;
    private String carCondition;
    private String price;
    private LocalDate date;
    private LocalTime time;
}
