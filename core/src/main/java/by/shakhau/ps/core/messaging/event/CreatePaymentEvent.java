package by.shakhau.ps.core.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatePaymentEvent {

    private UUID orderId;
    private UUID userId;
    private BigDecimal paymentAmount;
    private ShortPaymentCardDto card;
    private String cvv;
}
