package spring.ws.database.dto.read;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class SellerReadDto {

    private Long phoneNumber;
    private String fio;
    private Integer inn;
    private String email;

}


