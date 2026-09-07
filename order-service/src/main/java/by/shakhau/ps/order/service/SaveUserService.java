package by.shakhau.ps.order.service;

import by.shakhau.ps.core.service.model.ShortUser;

import java.util.Collection;

public interface SaveUserService {

    void save(ShortUser user);
    void save(Collection<ShortUser> users);
}
