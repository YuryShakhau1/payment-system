package by.shakhau.ps.auth.messaging.consumer;

import by.shakhau.ps.auth.service.UserCredentialService;
import by.shakhau.ps.core.messaging.event.UserCreationFailedEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserCreationFailedConsumer {

    private static final String TOPIC = "user.creation.failed";

    private final UserCredentialService userCredentialService;

    @KafkaListener(topics = TOPIC, groupId = "auth-service")
    public void consume(List<UserCreationFailedEvent> events, Acknowledgment ack) {
        List<UUID> userIds = events.stream()
                .map(UserCreationFailedEvent::getUserId)
                .toList();
        userCredentialService.updateActive(userIds, false);

        ack.acknowledge();
    }
}
