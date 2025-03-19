package spring.ws.database.dto.write;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class CarEditDto {
    @Pattern(regexp = "[A-Z]\\d{3}[A-Z]{2}", message = "Автомобильный номер должен быть в формате X777XX")
    String autoNumber;
    @Pattern(regexp = "\\d+", message = "Пробег должен быть числом")
    String km;
    @NotBlank(message = "Состояние автомобиля не должно быть пустым")
    String carCondition;
    @Pattern(regexp = "\\d+(\\.\\d{2})?", message = "Цена должна быть числом с двумя десятичными знаками (например, 10000.00)")
    String price;
}
