package by.shakhau.fake.bank.controller.dto;

import by.shakhau.fake.bank.service.model.PaymentCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class PaymentRequest {

    private PaymentCard card;
    private BigDecimal paymentAmount;
}
