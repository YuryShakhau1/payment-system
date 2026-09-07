package by.shakhau.ps.order.repository.entity;

import by.shakhau.ps.core.repository.entity.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_snapshots")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@EqualsAndHashCode(of = { "productId", "price" }, callSuper = false)
public class ProductSnapshotEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID productId;
    private String name;
    private BigDecimal price;
}
