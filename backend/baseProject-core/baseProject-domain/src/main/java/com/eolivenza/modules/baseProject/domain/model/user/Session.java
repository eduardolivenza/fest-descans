package com.eolivenza.modules.baseProject.domain.model.user;

public class Session {

    private User user;
    private String token;

    public Session(User currentUser) {
        this.user = currentUser;
        this.token = "xxhd3weefe";
        //generate token and store in sessions table
    }

    public String getToken(){
        return this.token;
    }
}
