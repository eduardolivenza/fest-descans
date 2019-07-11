package com.eolivenza.modules.baseProject.repositories.domain.users;

import com.eolivenza.modules.baseProject.application.Mapper;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import com.eolivenza.modules.baseProject.domain.model.user.UserRights;
import org.springframework.stereotype.Component;

@Component
public class UsersMapper implements Mapper<User, UserJpa> {

    @Override
    public User toDomain(UserJpa object) {
        return new User()
                .setEmail( object.getEmail())
                .setName( object.getName())
                .setSurname(object.getSurname())
                .setPassword(object.getPassword())
                .setRights( UserRights.valueOf(object.getUserRights()));
    }

    /**
     * Creates the JPA configuration object out of the configuration domain object
     *
     * @param object the configuration domain object
     * @return the JPA configuration object
     */
    @Override
    public UserJpa fromDomain(User object) {
        return new UserJpa(
                object.getEmail(),
                object.getName(),
                object.getSurname(),
                object.getPassword(),
                object.getRights().toString()
        );
    }
}
