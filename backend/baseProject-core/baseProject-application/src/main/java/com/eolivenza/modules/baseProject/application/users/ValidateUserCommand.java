package com.eolivenza.modules.baseProject.application.users;

import com.eolivenza.modules.baseProject.application.Command;

public class ValidateUserCommand implements Command {

    private String email;
    private String userPassword;

    public String getUserPassword() {
        return userPassword;
    }
    public String getEmail() {
        return this.email;
    }

    public ValidateUserCommand(String email, String userPassword) {
        this.email = email;
        this.userPassword = userPassword;
    }
}
