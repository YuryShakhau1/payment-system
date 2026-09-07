package by.shakhau.ps.auth.messaging.mapper;

import by.shakhau.ps.core.messaging.event.UserCreatedEvent;
import by.shakhau.ps.auth.service.model.UserInfo;
import by.shakhau.ps.core.messaging.event.UserUpdatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEventMapper {

    @Mapping(source = "tempPassword", target = "password")
    UserInfo toUserInfo(UserCreatedEvent event);
    UserInfo toUserInfo(UserUpdatedEvent event);
}
