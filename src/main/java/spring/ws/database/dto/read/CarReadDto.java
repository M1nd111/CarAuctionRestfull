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

    private Long autoNumber;
    private String markAndModelName;
    private Year year;
    private String km;
    private String feel;
    private Long price;

}
