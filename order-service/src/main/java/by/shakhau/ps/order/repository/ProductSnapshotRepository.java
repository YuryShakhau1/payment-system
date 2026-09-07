package by.shakhau.ps.order.repository;

import by.shakhau.ps.order.repository.entity.ProductSnapshotEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface ProductSnapshotRepository extends JpaRepository<ProductSnapshotEntity, UUID> {

    Optional<ProductSnapshotEntity> findByProductIdAndPrice(UUID productId, BigDecimal price);
}
