package by.shakhau.ps.payment.service.model;

import by.shakhau.ps.core.service.model.ShortUser;
import by.shakhau.ps.payment.repository.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Payment implements WithUser {

    private UUID id;
    private UUID orderId;
    private UUID userId;
    private PaymentStatus status;
    private Instant createdAt;
    private BigDecimal paymentAmount;

    private ShortUser user;
}
