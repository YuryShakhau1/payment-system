package by.shakhau.ps.order.service;

import by.shakhau.ps.core.service.model.ShortUser;

import java.util.UUID;

public interface UserService {

    ShortUser fetchById(UUID id);
}
