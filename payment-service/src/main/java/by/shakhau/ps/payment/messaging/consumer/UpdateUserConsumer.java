package by.shakhau.ps.payment.messaging.consumer;

import by.shakhau.ps.core.messaging.event.UserUpdatedEvent;
import by.shakhau.ps.payment.messaging.mapper.UserEventMapper;
import by.shakhau.ps.payment.service.UserService;
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
    private final UserService userService;

    @KafkaListener(topics = TOPIC, groupId = "payment-service")
    public void consume(List<UserUpdatedEvent> events, Acknowledgment ack) {
        userService.save(events.stream()
                .map(userEventMapper::toUser)
                .toList());

        ack.acknowledge();
    }
}
