package com.eolivenza.modules.baseProject.controller.http.rest;

import com.eolivenza.modules.baseProject.application.*;
import com.eolivenza.modules.baseProject.application.security.BaseProjectGrantPermission;
import com.eolivenza.modules.baseProject.application.users.commands.AddUserCommand;
import com.eolivenza.modules.baseProject.application.users.commands.RemoveUserCommand;
import com.eolivenza.modules.baseProject.application.users.queries.ValidateUserCommand;
import com.eolivenza.modules.baseProject.controller.http.rest.mapper.UsersResourceMapper;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.SessionResource;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.UserResource;
import com.eolivenza.modules.baseProject.controller.http.rest.resources.ValUserResource;
import com.eolivenza.modules.baseProject.domain.model.user.Session;
import com.eolivenza.modules.baseProject.domain.model.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(value = "Users")
@RestController
public class UsersController {

    private final UsersResourceMapper usersResourceMapper;
    private final QueryHandler<Class<String>, Optional<User>> getUserQueryHandler;
    private final QueryHandler<Class<Void>, List<User>> getAllUsersQueryHandler;
    private final CommandHandler<AddUserCommand> addUserCommandCommandHandler;
    private final QueryHandler<ValidateUserCommand, Session> validateUserQueryHandler;
    private final CommandHandler<RemoveUserCommand> removeUserCommandHandler;

    @Autowired
    public UsersController(
            QueryHandler<Class<String>, Optional<User>> getUserQueryHandler,
            UsersResourceMapper usersResourceMapper,
            CommandHandler<AddUserCommand> addUserCommandCommandHandler,
            QueryHandler<ValidateUserCommand, Session> validateUserQueryHandler,
            QueryHandler<Class<Void>, List<User>> getAllUsersQueryHandler,
            CommandHandler<RemoveUserCommand> removeUserCommandHandler) {
        this.getUserQueryHandler = getUserQueryHandler;
        this.usersResourceMapper = usersResourceMapper;
        this.addUserCommandCommandHandler = addUserCommandCommandHandler;
        this.validateUserQueryHandler = validateUserQueryHandler;
        this.getAllUsersQueryHandler = getAllUsersQueryHandler;
        this.removeUserCommandHandler = removeUserCommandHandler;
    }

    /**
     * Adds a new user
     *
     * @param userResource new user Resource
     */
    @ApiOperation(value = "Adds a new user to the system")
    @PostMapping(path = "/users")
    //@RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public void addUser(
            @RequestBody UserResource userResource) {
        User  user = usersResourceMapper.toFirstType(userResource);
        AddUserCommand command = new AddUserCommand(user.getEmail(), user.getPassword(), user.getName(), user.getSurname(), user.getRights());
        addUserCommandCommandHandler.accept(command);
    }

    @ApiOperation(value = "Validate an user to enter into the system")
    @PostMapping(path = "/users/authenticate")
    //@RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public SessionResource validateUser(
            @RequestBody ValUserResource userResource) {
        ValidateUserCommand command = new ValidateUserCommand(userResource.email, userResource.password);
        Session session = validateUserQueryHandler.apply(command);
        SessionResource sessionResource = new SessionResource();
        sessionResource.token = session.getToken();
        return sessionResource;
    }

    @ApiOperation(value = "Validate an user to enter into the system")
    @GetMapping(path = "/users", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    //@RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public List<UserResource> getAllUsers() {
        List<User> usersList = getAllUsersQueryHandler.apply(Void.TYPE);
        return usersList.stream().map(usersResourceMapper::toSecondType).collect(Collectors.toList());
    }

    @ApiOperation(value = "Removes an user")
    @DeleteMapping(path = "/users/{email}")
    @RolesAllowed(BaseProjectGrantPermission.MASTER_FILE_EDITION)
    public void removeInstrument(
            @ApiParam(required = true, value = "Email of the user to delete", example = "currentUser@mail.com")
            @PathVariable String email) {

        removeUserCommandHandler.accept(new RemoveUserCommand(email.replace("&#46;",".")));
    }

}
