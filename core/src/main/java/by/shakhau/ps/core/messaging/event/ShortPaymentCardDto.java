package by.shakhau.ps.core.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShortPaymentCardDto {

    private String number;
    private String holder;
    private LocalDate expirationDate;
}
