package by.shakhau.fake.bank.service;

import by.shakhau.fake.bank.service.model.PaymentCard;

import java.math.BigDecimal;

public interface PaymentService {

    String create(PaymentCard card, BigDecimal amount);
}
