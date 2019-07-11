package com.eolivenza.modules.baseProject.application.users;

import com.eolivenza.modules.baseProject.application.Command;
import com.eolivenza.modules.baseProject.domain.model.user.UserRights;

public class AddUserCommand implements Command {

    private UserRights rights;
    private String email;
    private String userPassword;
    private String userName;
    private String userSurname;

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserName() {
        return this.userName;
    }

    public UserRights getUserRights() {
        return this.rights;
    }

    public AddUserCommand(String email, String userPassword, String userName, String userSurname, UserRights rights) {
        this.email = email;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userSurname = userSurname;
        this.rights = rights;
    }
}
