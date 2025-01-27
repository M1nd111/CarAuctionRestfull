package spring.ws.database.dto.read;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.Year;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class CarReadDto {
    @Pattern(regexp = "[A-Z]\\d{3}[A-Z]{2}", message = "Автомобильный номер должен быть в формате X777XX")
    private String autoNumber;

    @NotBlank(message = "Название марки и модели не должно быть пустым")
    private String markAndModelName;

    @Pattern(regexp = "\\d{4}", message = "Год выпуска должен быть числом из 4 цифр")
    private String year;

    @Pattern(regexp = "\\d+", message = "Пробег должен быть числом")
    private String km;

    @NotBlank(message = "Состояние автомобиля не должно быть пустым")
    private String carCondition;

    @Pattern(regexp = "\\d+(\\.\\d{2})?", message = "Цена должна быть числом с двумя десятичными знаками (например, 10000.00)")
    private String price;

    @Pattern(regexp = "\\+\\d{11}", message = "Номер телефона продавца должен быть в формате +79998887766")
    private String sellerPhone;
}
