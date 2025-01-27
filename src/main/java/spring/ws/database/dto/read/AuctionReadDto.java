package spring.ws.database.dto.read;

import jakarta.persistence.Column;
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
public class AuctionReadDto {
    @NotNull
    private Long id;

    @Pattern(regexp = "[A-Z]\\d{3}[A-Z]{2}", message = "Автомобильный номер должен быть в формате X777XX")
    private String autoNumber;

}
