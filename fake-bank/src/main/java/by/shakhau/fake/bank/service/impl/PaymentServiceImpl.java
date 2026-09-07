package by.shakhau.fake.bank.service.impl;

import by.shakhau.fake.bank.service.PaymentService;
import by.shakhau.fake.bank.service.model.PaymentCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final SecureRandom random = new SecureRandom();

    @Override
    public String create(PaymentCard card, BigDecimal amount) {
        if (random.nextInt() % 2 == 0) {
            return "SUCCESS";
        }

        return "FAILED";
    }
}
