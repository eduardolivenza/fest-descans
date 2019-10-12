package com.eolivenza.modules.baseProject.application.users;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String userId){
        super("User ".concat(userId).concat(" already registered"));
    }
}
