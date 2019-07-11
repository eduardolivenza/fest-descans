package com.eolivenza.modules.baseProject.application.users.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.ConfigurationRepository;
import com.eolivenza.modules.baseProject.application.repositories.UsersRepository;
import com.eolivenza.modules.baseProject.domain.model.configuration.Configuration;
import com.eolivenza.modules.baseProject.domain.model.user.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class GetAllUsersQueryHandler implements QueryHandler<Class<Void>, List<User>> {

    private final UsersRepository usersRepository;

    @Inject
    public GetAllUsersQueryHandler(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Retrieve the {@link Configuration}
     *
     * @param getConfigurationRequest void class
     * @return a {@link Configuration}
     **/
    @DomainStrictTransactional
    @Override
    public List<User> apply(Class<Void> getConfigurationRequest) {
        return (usersRepository.retrieveAll());
    }

    @Override
    public String getName() { return "GetAllUsers"; }


}
