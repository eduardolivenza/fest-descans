package com.eolivenza.modules.baseProject.application.users;


import com.eolivenza.modules.baseProject.application.CommandHandler;
import com.eolivenza.modules.baseProject.application.annotations.DomainStrictTransactional;
import com.eolivenza.modules.baseProject.application.repositories.UsersRepository;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import com.eolivenza.modules.baseProject.domain.model.user.UserRights;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AddUserCommandHandler implements CommandHandler<AddUserCommand> {
    private final UsersRepository usersRepository;

    private Logger logger = LoggerFactory.getLogger(AddUserCommandHandler.class);

    @Inject
    public AddUserCommandHandler(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(AddUserCommand addUserCommand) {
        User newUser = toDomain(addUserCommand);
        logger.debug(" Element user validated");
        if (usersRepository.exists(newUser.getEmail())) {
            User currentUser = usersRepository.retrieve(newUser.getEmail());
            currentUser.overwriteWith(newUser);
            usersRepository.update(currentUser);
        }
        else {
            usersRepository.create(newUser);
        }
    }

    private User toDomain(AddUserCommand addUserCommand) {
        User user = new User()
                .setEmail(addUserCommand.getEmail())
                .setName(addUserCommand.getUserName())
                .setSurname(addUserCommand.getUserSurname())
                .setPassword(addUserCommand.getUserPassword());
        if (addUserCommand.getUserRights() != null){
            user.setRights(addUserCommand.getUserRights());
        }
        else{
            user.setRights(UserRights.defaultUser);
        }
        return user;
    }

    @Override
    public String getName() { return "Add user"; }

}


