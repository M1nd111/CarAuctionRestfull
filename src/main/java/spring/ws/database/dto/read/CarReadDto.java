package spring.ws.database.dto.read;

import lombok.*;

import java.time.Year;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class CarReadDto {

    private String autoNumber;
    private String markAndModelName;
    private String year;
    private String km;
    private String carCondition;
    private String price;
    private String sellerPhone;

}
