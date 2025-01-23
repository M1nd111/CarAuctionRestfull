package spring.ws.database.dto.read;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class BuyerReadDto {
    private String phoneNumber;
    private String fio;
    private String passportNumber;
    private String email;
    private String password;
    private String role;

}
