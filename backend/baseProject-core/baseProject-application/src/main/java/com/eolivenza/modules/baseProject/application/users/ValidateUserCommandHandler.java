package com.eolivenza.modules.baseProject.application.users;


import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.UsersRepository;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ValidateUserCommandHandler implements CommandHandler<ValidateUserCommand> {
    private final UsersRepository usersRepository;

    private Logger logger = LoggerFactory.getLogger(ValidateUserCommandHandler.class);

    @Inject
    public ValidateUserCommandHandler(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(ValidateUserCommand validateUserCommand) {
        logger.debug(" Element user validated");
        if (usersRepository.exists(validateUserCommand.getEmail())) {
            User currentUser = usersRepository.retrieve(validateUserCommand.getEmail());
            if (!(currentUser.getPassword().equals(validateUserCommand.getUserPassword()))){
                throw new UserNotCorrectException();
            }
        }
        else {
            throw new UserNotExistsException();
        }

    }

    @Override
    public String getName() { return "Add user"; }

}


