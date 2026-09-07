package by.shakhau.ps.auth.messaging.consumer;

import by.shakhau.ps.auth.service.UserCredentialService;
import by.shakhau.ps.core.messaging.event.DeactivateUserCredentialsEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class DeactivateUserCredentialsConsumer {

    private static final String TOPIC = "user.credentials.deactivate";

    private final UserCredentialService userCredentialService;

    @KafkaListener(topics = TOPIC, groupId = "auth-service")
    public void consume(List<DeactivateUserCredentialsEvent> events, Acknowledgment ack) {
        List<UUID> userIds = events.stream()
                .map(DeactivateUserCredentialsEvent::getUserId)
                .toList();
        userCredentialService.updateActive(userIds, false);

        ack.acknowledge();
    }
}
