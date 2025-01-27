package spring.ws.database.dto.read;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class BuyerReadDto {
    @Pattern(regexp = "\\d{11}", message = "Номер телефона должен быть в формате +79998887766")
    private String phoneNumber;

    @Pattern(regexp = "[А-Яа-яA-Za-z\\s]+", message = "ФИО должно состоять только из букв")
    private String fio;

    @Pattern(regexp = "\\d{10}", message = "Номер паспорта должен быть в формате 1234 567890")
    private String passportNumber;

    @Email(message = "Некорректный формат email")
    private String email;

    @Size(min = 4, message = "Пароль должен содержать минимум 4 символа")
    private String password;

    @Pattern(regexp = "(buyer|seller)", message = "Роль должна быть одной из: buyer, seller")
    private String role;
}
