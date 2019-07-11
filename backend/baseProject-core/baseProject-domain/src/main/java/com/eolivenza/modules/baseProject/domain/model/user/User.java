package com.eolivenza.modules.baseProject.domain.model.user;

import com.eolivenza.modules.baseProject.domain.model.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class User  extends Entity<User> {

    @NotBlank
    private String email;
    @NotNull
    private String password;
    private String name;
    private String surname;
    private UserRights rights;

    public User(){ }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserRights getRights() {
        return rights;
    }

    public User setRights(UserRights rights) {
        this.rights = rights;
        return this;
    }

    public void overwriteWith(User otherUser) {
        setName(otherUser.getName());
        setSurname(otherUser.getSurname() );
        setPassword(otherUser.getPassword());
    }

    @Override
    public int hashCodeCalculation() {
        return 0;
    }

    @Override
    public boolean hasSameIdentity(User other) {
        return false;
    }
}
