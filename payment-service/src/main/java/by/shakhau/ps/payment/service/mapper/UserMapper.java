package by.shakhau.ps.payment.service.mapper;

import by.shakhau.ps.core.service.model.ShortUser;
import by.shakhau.ps.payment.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserEntity toEntity(ShortUser user);
    ShortUser toModel(UserEntity user);
}
