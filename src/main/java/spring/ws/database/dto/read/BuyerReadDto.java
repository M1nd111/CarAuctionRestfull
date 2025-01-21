package spring.ws.database.dto.read;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class BuyerReadDto {
    private Long phoneNumber;
    private String fio;
    private Integer passportNumber;
    private String email;

}
