package by.shakhau.ps.order.service.mapper;

import by.shakhau.ps.order.repository.entity.UserEntity;
import by.shakhau.ps.core.service.model.ShortUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserEntity toEntity(ShortUser user);
    ShortUser toModel(UserEntity user);
}
