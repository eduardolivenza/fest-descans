package com.eolivenza.modules.baseProject.repositories.domain.users;

import com.eolivenza.modules.baseProject.application.repositories.UsersRepository;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private final UsersMapper usersMapper;
    private final UsersRepositoryJpaSpringData usersRepositoryJpaSpringData;

    @Autowired
    public UsersRepositoryImpl(UsersMapper usersMapper, UsersRepositoryJpaSpringData configurationRepositoryJpaSpringData) {
        this.usersMapper = usersMapper;
        this.usersRepositoryJpaSpringData = configurationRepositoryJpaSpringData;
    }

    @Override
    public User update(User entity) {
        UserJpa userJpa = usersMapper.fromDomain(entity);
        userJpa = usersRepositoryJpaSpringData.saveAndFlush(userJpa);
        return usersMapper.toDomain(userJpa);
    }

    @Override
    public User retrieve(String email) {
        User user = null;
        Optional<UserJpa> optionalUserJpa =
                usersRepositoryJpaSpringData.findById(email);

        if (optionalUserJpa.isPresent())
            user = usersMapper.toDomain(optionalUserJpa.get());
        return user;
    }

    @Override
    public List<User> retrieveAll() {
        List<UserJpa> usersJpaList = usersRepositoryJpaSpringData.findAll();
        return usersJpaList.stream().map(usersMapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public boolean exists(String email) {
        return usersRepositoryJpaSpringData.existsById(email);
    }

    @Override
    public User create(User entity) {
        UserJpa userJpa = usersMapper.fromDomain(entity);
        userJpa = usersRepositoryJpaSpringData.saveAndFlush(userJpa);
        return usersMapper.toDomain(userJpa);
    }

    @Override
    public void delete(String email) {
        usersRepositoryJpaSpringData.deleteById(email);
    }
}
