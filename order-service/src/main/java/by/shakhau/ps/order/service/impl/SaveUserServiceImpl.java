package by.shakhau.ps.order.service.impl;

import by.shakhau.ps.core.service.model.ShortUser;
import by.shakhau.ps.order.repository.UserRepository;
import by.shakhau.ps.order.service.SaveUserService;
import by.shakhau.ps.order.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SaveUserServiceImpl implements SaveUserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    @Transactional
    @Override
    public void save(ShortUser user) {
        repository.save(mapper.toEntity(user));
    }

    @Transactional
    @Override
    public void save(Collection<ShortUser> users) {
        repository.saveAll(users.stream().map(mapper::toEntity).toList());
    }
}
