package by.shakhau.ps.payment.service;

import by.shakhau.ps.core.service.model.ShortUser;

import java.util.Collection;
import java.util.UUID;

public interface UserService {

    ShortUser fetchById(UUID id);
    void save(ShortUser user);
    void save(Collection<ShortUser> users);
}
