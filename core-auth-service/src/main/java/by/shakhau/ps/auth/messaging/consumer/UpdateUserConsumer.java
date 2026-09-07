package by.shakhau.ps.auth.messaging.consumer;

import by.shakhau.ps.auth.messaging.mapper.UserEventMapper;
import by.shakhau.ps.auth.service.UserCredentialService;
import by.shakhau.ps.auth.service.model.UserInfo;
import by.shakhau.ps.core.messaging.event.UserUpdatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UpdateUserConsumer {

    private static final String TOPIC = "user.updated";

    private final UserEventMapper userEventMapper;
    private final UserCredentialService userCredentialService;

    @KafkaListener(topics = TOPIC, groupId = "user-service")
    public void consume(List<UserUpdatedEvent> events, Acknowledgment ack) {
        userCredentialService.update(events.stream()
                .map(userEventMapper::toUserInfo)
                .toList());

        ack.acknowledge();
    }
}
