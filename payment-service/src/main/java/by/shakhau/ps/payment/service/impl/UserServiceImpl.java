package by.shakhau.ps.payment.service.impl;

import by.shakhau.ps.core.service.model.ShortUser;
import by.shakhau.ps.payment.client.UserClient;
import by.shakhau.ps.payment.repository.UserRepository;
import by.shakhau.ps.payment.service.UserService;
import by.shakhau.ps.payment.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserClient client;
    private final UserRepository repository;

    @Override
    public ShortUser fetchById(UUID id) {
        ShortUser foundUser = findById(id);
        if (foundUser == null) {
            try {
                foundUser = client.findUserById(id);
            } catch (Exception e) {
                return null;
            }
            if (foundUser != null) {
                try {
                    save(foundUser);
                } catch (DataIntegrityViolationException e) {
                    foundUser = findById(id);
                }
            }
        }
        return foundUser;
    }

    @Override
    public void save(ShortUser user) {
        repository.save(mapper.toEntity(user));
    }

    @Override
    public void save(Collection<ShortUser> users) {
        repository.saveAll(users.stream().map(mapper::toEntity).toList());
    }

    private ShortUser findById(UUID id) {
        return mapper.toModel(repository.findById(id).orElse(null));
    }
}
