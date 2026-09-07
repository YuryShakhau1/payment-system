package by.shakhau.ps.core.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentFinishedEvent {

    private UUID paymentId;
    private UUID orderId;
    private String paymentStatus;
}
