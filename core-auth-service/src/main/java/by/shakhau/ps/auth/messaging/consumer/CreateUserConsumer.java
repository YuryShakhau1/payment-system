package by.shakhau.ps.auth.messaging.consumer;

import by.shakhau.ps.core.messaging.event.UserCreatedEvent;
import by.shakhau.ps.auth.messaging.mapper.UserEventMapper;
import by.shakhau.ps.auth.messaging.producer.CreatedUserCredentialsProducer;
import by.shakhau.ps.auth.service.UserCredentialService;
import by.shakhau.ps.auth.service.model.UserInfo;
import by.shakhau.ps.core.messaging.event.UserCredentialsCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CreateUserConsumer {

    private static final String TOPIC = "user.created";

    private final CreatedUserCredentialsProducer createdUserCredentialsProducer;
    private final UserEventMapper userEventMapper;
    private final UserCredentialService userCredentialService;

    @KafkaListener(topics = TOPIC, groupId = "user-service")
    public void consume(List<UserCreatedEvent> events, Acknowledgment ack) {
        for (UserCreatedEvent event : events) {
            UserInfo userInfo = userEventMapper.toUserInfo(event);
            userInfo.setPasswordActive(false);
            try {
                userCredentialService.registerUser(userInfo, event.getRole());
                createdUserCredentialsProducer.send(
                        new UserCredentialsCreatedEvent(userInfo.getUserId(), true));
            } catch (Exception e) {
                createdUserCredentialsProducer.send(
                        new UserCredentialsCreatedEvent(userInfo.getUserId(), false));
            }
        }

        ack.acknowledge();
    }
}
