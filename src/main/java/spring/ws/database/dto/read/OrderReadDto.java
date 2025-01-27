package spring.ws.database.dto.read;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class OrderReadDto {
    @NotNull
    private Long orderNumber;

    @Pattern(regexp = "[A-Z]\\d{3}[A-Z]{2}", message = "Автомобильный номер должен быть в формате X777XX")
    private String autoNumber;

    @Pattern(regexp = "\\+\\d{11}", message = "Номер телефона продавца должен быть в формате +79998887766")
    private Long sellerPhone;

    @Pattern(regexp = "\\+\\d{11}", message = "Номер телефона покупателя должен быть в формате +79998887766")
    private Long buyerPhone;

    @Pattern(regexp = "\\d+(\\.\\d{2})?", message = "Начальная ставка должна быть числом с двумя десятичными знаками (например, 1000.00)")
    private String initialBid;

    @NotNull(message = "Дата не должна быть null")
    private LocalDate date;

    @NotNull(message = "Время не должно быть null")
    private LocalTime time;

    private Boolean status;
}
