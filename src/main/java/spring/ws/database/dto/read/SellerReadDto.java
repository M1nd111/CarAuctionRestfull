package spring.ws.database.dto.read;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class SellerReadDto {
    private String fio;
    private String phoneNumber;
    private String email;
    private String inn;
    private String password;
    private String role;
}


