package com.eolivenza.modules.baseProject.repositories.domain.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(schema = "baseproject_dm_tables", name = "USERS")
public class UserJpa {
    @Id
    private String email;
    private String name;
    private String surname;
    private String password;
    private String userRights;


    public UserJpa() {
        //JPA demands it
    }

    public UserJpa( String email, String name, String surname, String password, String userRights ) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.userRights = userRights;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRights() {
        return userRights;
    }

    public void setUserRights(String userRights) {
        this.userRights = userRights;
    }
}
