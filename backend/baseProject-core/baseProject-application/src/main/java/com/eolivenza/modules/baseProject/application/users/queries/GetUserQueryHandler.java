package com.eolivenza.modules.baseProject.application.users.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.ConfigurationRepository;
import com.eolivenza.modules.baseProject.application.repositories.UsersRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.domain.model.user.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class GetUserQueryHandler implements QueryHandler<Class<String>, Optional<User>> {
    private final UsersRepository usersRepository;

    @Inject
    public GetUserQueryHandler(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @DomainStrictTransactional
    @Override
    public Optional<User> apply(Class<String> getUserRequest) {
        return null;
        //return Optional.ofNullable(usersRepository.retrieve(getUserRequest));
    }

    @Override
    public String getName() { return "Get User"; }


}
