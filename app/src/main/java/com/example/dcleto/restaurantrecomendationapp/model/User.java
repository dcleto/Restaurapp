package com.example.dcleto.restaurantrecomendationapp.model;

/**
 * Created by Daniel on 11/07/2017.
 */

public class User {
    private int id;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;
    private String avatar;

    public User(String firstName, String lastName, String password,String confirmPassword, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.confirmPassword = confirmPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
