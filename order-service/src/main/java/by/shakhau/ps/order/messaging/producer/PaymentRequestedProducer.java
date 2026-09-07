package by.shakhau.ps.order.messaging.producer;

import by.shakhau.ps.core.messaging.event.PaymentRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRequestedProducer {

    private static final String TOPIC = "payment.requested";
    private final KafkaTemplate<String, PaymentRequestedEvent> template;

    public void send(PaymentRequestedEvent event) {
        try {
            template.send(TOPIC, event.getOrderId().toString(), event).get();
        } catch (Exception e) {
            throw new KafkaException(e.getMessage(), e);
        }
    }
}
