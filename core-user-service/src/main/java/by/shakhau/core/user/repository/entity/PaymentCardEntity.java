package by.shakhau.core.user.repository.entity;

import by.shakhau.core.user.repository.converter.EncryptStringConverter;
import by.shakhau.ps.core.repository.entity.AuditableEntity;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payment_cards")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@EqualsAndHashCode(of = {"number"}, callSuper = false)
public class PaymentCardEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Convert(converter = EncryptStringConverter.class)
    private String number;

    private String holder;
    private LocalDate expirationDate;
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
