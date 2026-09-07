package by.shakhau.ps.auth.messaging.consumer;

import by.shakhau.ps.auth.service.UserCredentialService;
import by.shakhau.ps.core.messaging.event.UserStatusUpdatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UpdateUserStatusConsumer {

    private static final String TOPIC = "user.status.updated";

    private final UserCredentialService userCredentialService;

    @KafkaListener(topics = TOPIC, groupId = "user-service")
    public void consume(List<UserStatusUpdatedEvent> events, Acknowledgment ack) {
        List<UUID> activeUserIds = events.stream()
                .filter(UserStatusUpdatedEvent::getActive)
                .map(UserStatusUpdatedEvent::getUserId)
                .toList();
        List<UUID> inactiveUserIds = events.stream()
                .filter(event -> !event.getActive())
                .map(UserStatusUpdatedEvent::getUserId)
                .toList();
        if (!activeUserIds.isEmpty()) {
            userCredentialService.updateActive(activeUserIds, true);
        }

        if (!inactiveUserIds.isEmpty()) {
            userCredentialService.updateActive(inactiveUserIds, false);
        }

        ack.acknowledge();
    }
}
