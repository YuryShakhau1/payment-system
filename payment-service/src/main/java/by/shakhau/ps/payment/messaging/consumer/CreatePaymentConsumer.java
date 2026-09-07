package by.shakhau.ps.payment.messaging.consumer;

import by.shakhau.ps.core.component.Encryptor;
import by.shakhau.ps.core.messaging.event.CreatePaymentEvent;
import by.shakhau.ps.core.messaging.event.PaymentFinishedEvent;
import by.shakhau.ps.payment.client.ExternalPaymentClient;
import by.shakhau.ps.payment.client.dto.PaymentCardFull;
import by.shakhau.ps.payment.client.dto.PaymentRequest;
import by.shakhau.ps.payment.client.dto.PaymentStatus;
import by.shakhau.ps.payment.client.mapper.PaymentCardDtoMapper;
import by.shakhau.ps.payment.messaging.producer.PaymentFinishedProducer;
import by.shakhau.ps.payment.service.PaymentService;
import by.shakhau.ps.payment.service.model.Payment;
import by.shakhau.ps.payment.service.model.PaymentCard;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static by.shakhau.ps.payment.repository.entity.PaymentStatus.FAILED;
import static by.shakhau.ps.payment.repository.entity.PaymentStatus.SUCCESS;

@Component
@RequiredArgsConstructor
public class CreatePaymentConsumer {

    private static final String TOPIC = "payment.create";

    private final Encryptor encryptor;
    private final PaymentCardDtoMapper paymentCardDtoMapper;
    private final ExternalPaymentClient externalPaymentClient;
    private final PaymentService paymentService;
    private final PaymentFinishedProducer paymentFinishedProducer;

    @KafkaListener(topics = TOPIC, groupId = "payment-service")
    public void consume(List<CreatePaymentEvent> events, Acknowledgment ack) {
        for (CreatePaymentEvent event : events) {
            Payment payment = createPayment(event);

            PaymentCardFull encryptedCard = createEncryptedCard(event);

            PaymentStatus status = processPayment(event, encryptedCard);
            updatePaymentStatus(payment, status);

            responsePaymentFinished(payment, status);
        }

        ack.acknowledge();
    }

    private Payment createPayment(CreatePaymentEvent event) {
        var payment = Payment.builder()
                .id(UUID.randomUUID())
                .orderId(event.getOrderId())
                .userId(event.getUserId())
                .paymentAmount(event.getPaymentAmount())
                .build();
        paymentService.create(payment);
        return payment;
    }

    private PaymentCardFull createEncryptedCard(CreatePaymentEvent event) {
        PaymentCard card = paymentCardDtoMapper.toModel(event.getCard());
        card.setNumber(encryptor.decrypt(card.getNumber()));
        card.setHolder(encryptor.decrypt(card.getHolder()));
        return paymentCardDtoMapper.toDto(
                encryptor.decrypt(event.getCvv()), card);
    }

    private PaymentStatus processPayment(CreatePaymentEvent event, PaymentCardFull fullCardRequest) {
        return externalPaymentClient.processPayment(
                PaymentRequest.builder()
                        .card(fullCardRequest)
                        .paymentAmount(event.getPaymentAmount())
                        .build());
    }

    private void updatePaymentStatus(Payment payment, PaymentStatus status) {
        if ("SUCCESS".equals(status.getStatus())) {
            payment.setStatus(SUCCESS);
        } else {
            payment.setStatus(FAILED);
        }

        paymentService.update(payment);
    }

    private void responsePaymentFinished(Payment payment, PaymentStatus status) {
        paymentFinishedProducer.send(PaymentFinishedEvent.builder()
                .paymentId(payment.getId())
                .orderId(payment.getOrderId())
                .paymentStatus(status.getStatus())
                .build());
    }
}
