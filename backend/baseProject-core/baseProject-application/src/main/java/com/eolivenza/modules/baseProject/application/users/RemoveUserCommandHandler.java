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
public class RemoveUserCommandHandler implements CommandHandler<RemoveUserCommand> {

    private final UsersRepository usersRepository;

    private Logger logger = LoggerFactory.getLogger(RemoveUserCommandHandler.class);

    @Inject
    public RemoveUserCommandHandler(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @DomainStrictTransactional
    @Override
    public void accept(RemoveUserCommand removeUserCommand) {
        usersRepository.delete(removeUserCommand.getEmail());
    }

    @Override
    public String getName() { return "Add user"; }

}


