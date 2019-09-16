package com.eolivenza.modules.baseProject.application.users.queries;

import com.eolivenza.modules.baseProject.application.QueryHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.UsersRepository;
import com.eolivenza.modules.baseProject.application.users.UserNotCorrectException;
import com.eolivenza.modules.baseProject.application.users.UserNotExistsException;
import com.eolivenza.modules.baseProject.domain.model.user.Session;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ValidateUserCommandHandler implements QueryHandler<ValidateUserCommand, Session> {

    private final UsersRepository usersRepository;

    private Logger logger = LoggerFactory.getLogger(ValidateUserCommandHandler.class);

    @Inject
    public ValidateUserCommandHandler(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @DomainStrictTransactional
    @Override
    public Session apply(ValidateUserCommand validateUserCommand) {
        logger.debug(" Element user validated");
        Session session = null;
        if (usersRepository.exists(validateUserCommand.getEmail())) {
            User currentUser = usersRepository.retrieve(validateUserCommand.getEmail());
            if (currentUser.getPassword().equals(validateUserCommand.getUserPassword())) {
                session = new Session(currentUser);
            }
            else {
                throw new UserNotCorrectException();
            }
        }
        else {
            throw new UserNotExistsException();
        }
        return session;
    }


    @Override
    public String getName() { return "Validates user"; }

}


