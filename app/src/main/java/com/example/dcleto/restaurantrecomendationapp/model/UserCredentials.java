package com.example.dcleto.restaurantrecomendationapp.model;

/**
 * Created by Daniel on 11/07/2017.
 */

public class UserCredentials {
    private String username;
    private String password;
    private String grant_type;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
        this.grant_type = "password";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
