package com.example.dcleto.restaurantrecomendationapp.model;

/**
 * Created by Daniel on 13/08/2017.
 */

public class UserRegistrationResponse {
    private User user;
    private String access_token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
