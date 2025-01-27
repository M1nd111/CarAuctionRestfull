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
public class SellerReadDto {
    @Pattern(regexp = "[А-Яа-яA-Za-z\\s]+", message = "ФИО должно состоять только из букв")
    private String fio;

    @Pattern(regexp = "\\d{11}", message = "Номер телефона должен быть в формате +79998887766")
    private String phoneNumber;

    @Email(message = "Некорректный формат email")
    private String email;

    @Pattern(regexp = "\\d{12}", message = "ИНН должен состоять из 12 цифр")
    private String inn;

    @Size(min = 4, message = "Пароль должен содержать минимум 4 символа")
    private String password;

    @Pattern(regexp = "(buyer|seller)", message = "Роль должна быть одной из: buyer, seller")
    private String role;
}


