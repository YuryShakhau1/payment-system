package by.shakhau.ps.order.service.mapper;

import by.shakhau.ps.order.client.dto.Product;
import by.shakhau.ps.order.repository.entity.ProductSnapshotEntity;
import by.shakhau.ps.order.service.model.ProductSnapshot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductSnapshotMapper {

    ProductSnapshot toModel(ProductSnapshotEntity entity);

    @Mapping(target = "productId", source = "id")
    ProductSnapshotEntity toEntity(Product product);
}
