package by.shakhau.ps.payment.client;

import by.shakhau.ps.core.service.model.ShortUser;

import java.util.UUID;

public class UserClientFallback implements UserClient {

    @Override
    public ShortUser findUserById(UUID userId) {
        return null;
    }
}