package by.shakhau.core.user.messaging.consumer;

import by.shakhau.ps.core.messaging.event.UserCreationFailedEvent;
import by.shakhau.ps.core.messaging.event.UserCredentialsCreatedEvent;
import by.shakhau.core.user.messaging.producer.UserCreationFailedProducer;
import by.shakhau.core.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CreatedUserCredentialsConsumer {

    private static final String TOPIC = "user.credentials.created";

    private final UserService userService;
    private final UserCreationFailedProducer userCreationFailedProducer;

    @KafkaListener(topics = TOPIC, groupId = "auth-service")
    public void consume(List<UserCredentialsCreatedEvent> events, Acknowledgment ack) {
        for (UserCredentialsCreatedEvent event : events) {
            if (!userService.existsById(event.getUserId())) {
                if (event.isCreated()) {
                    userCreationFailedProducer.send(new UserCreationFailedEvent(event.getUserId()));
                }

                ack.acknowledge();
                return;
            }

            userService.updateActiveStatus(event.getUserId(), event.isCreated());
        }

        ack.acknowledge();
    }
}
