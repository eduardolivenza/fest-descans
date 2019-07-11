package com.eolivenza.modules.baseProject.application.users;

import com.eolivenza.modules.baseProject.application.Command;

public class RemoveUserCommand implements Command {

    private String email;

    public String getEmail() {
        return this.email;
    }

    public RemoveUserCommand(String email) {
        this.email = email;
    }

}
