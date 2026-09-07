package by.shakhau.core.user.messaging.producer;

import by.shakhau.ps.core.messaging.event.UserCreationFailedEvent;
import by.shakhau.core.user.messaging.exception.KafkaConnectionException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreationFailedProducer {

    private static final String TOPIC = "user.creation.failed";
    private final KafkaTemplate<String, UserCreationFailedEvent> template;

    public void send(UserCreationFailedEvent event) {
        try {
            template.send(TOPIC, event.getUserId().toString(), event).get();
        } catch (Exception e) {
            throw new KafkaConnectionException(e.getMessage(), e);
        }
    }
}
