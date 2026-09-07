package by.shakhau.ps.core.messaging.event;

import by.shakhau.ps.core.service.model.serialization.SafePasswordDeserializer;
import by.shakhau.ps.core.service.model.serialization.SafePasswordSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreatedEvent {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;

    @JsonDeserialize(using = SafePasswordDeserializer.class)
    @JsonSerialize(using = SafePasswordSerializer.class)
    private StringBuilder tempPassword;
    private String role;
}
