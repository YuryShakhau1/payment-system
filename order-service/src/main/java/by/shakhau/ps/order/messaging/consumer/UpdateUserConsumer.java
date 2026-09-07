package by.shakhau.ps.order.messaging.consumer;

import by.shakhau.ps.core.messaging.event.UserUpdatedEvent;
import by.shakhau.ps.order.messaging.mapper.UserEventMapper;
import by.shakhau.ps.order.service.SaveUserService;
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
    private final SaveUserService saveUserService;

    @KafkaListener(topics = TOPIC, groupId = "order-service")
    public void consume(List<UserUpdatedEvent> events, Acknowledgment ack) {
        saveUserService.save(events.stream()
                .map(userEventMapper::toUser)
                .toList());

        ack.acknowledge();
    }
}
