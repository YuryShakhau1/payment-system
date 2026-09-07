package by.shakhau.ps.payment.service.mapper;

import by.shakhau.ps.payment.repository.entity.UserSumProjection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserSumMapper {

    UserSum toModel(UserSumProjection userSumProjection);
}
