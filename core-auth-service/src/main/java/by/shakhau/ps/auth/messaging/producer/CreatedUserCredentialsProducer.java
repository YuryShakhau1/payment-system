package by.shakhau.ps.auth.messaging.producer;

import by.shakhau.ps.auth.messaging.exception.KafkaConnectionException;
import by.shakhau.ps.core.messaging.event.UserCredentialsCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatedUserCredentialsProducer {

    private static final String TOPIC = "user.credentials.created";
    private final KafkaTemplate<String, UserCredentialsCreatedEvent> template;

    public void send(UserCredentialsCreatedEvent event) {
        try {
            template.send(TOPIC, event.getUserId().toString(), event).get();
        } catch (Exception e) {
            throw new KafkaConnectionException(e.getMessage(), e);
        }
    }
}
