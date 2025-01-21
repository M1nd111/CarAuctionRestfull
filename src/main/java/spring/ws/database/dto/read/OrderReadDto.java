package spring.ws.database.dto.read;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class OrderReadDto {

    private Long orderNumber;
    private Long autoNumber;
    private Long sellerPhone;
    private Long buyerPhone;
    private String orderNow;
    private LocalDate date;
    private LocalTime time;

}
