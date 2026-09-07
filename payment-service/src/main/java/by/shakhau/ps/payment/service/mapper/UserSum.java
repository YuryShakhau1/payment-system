package by.shakhau.ps.payment.service.mapper;

import by.shakhau.ps.core.service.model.ShortUser;
import by.shakhau.ps.payment.service.model.WithUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSum implements WithUser {

    private UUID id;
    private ShortUser user;
    private BigDecimal total;
}
