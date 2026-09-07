package by.shakhau.ps.order.repository.entity;

import by.shakhau.ps.core.repository.entity.AuditableEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class OrderEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID userId;
    private OrderStatus status;
    private BigDecimal totalPrice;
    private Boolean deleted;

    @OneToMany(fetch = FetchType.LAZY, cascade = { ALL }, mappedBy = "order", orphanRemoval = true)
    private List<OrderItemEntity> items;
}
