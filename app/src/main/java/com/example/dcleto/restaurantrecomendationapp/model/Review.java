package com.example.dcleto.restaurantrecomendationapp.model;

import java.util.Date;

/**
 * Created by daniel cleto on 8/8/2017.
 */

public class Review {
    private int id;
    private int userAvatar;
    private String avatar;
    private float foodRating;
    private float serviceRating;
    private int userId;
    private float rating;
    private int restaurantId;
    private String userName;
    private String comment;
    private Date date;

    public Review(String userName,Date date,int userAvatar, float rating, String comment) {
        this.userName = userName;
        this.date = date;
        this.userAvatar = userAvatar;
        this.rating = rating;
        this.comment = comment;
    }

    public Review(int resturantId, float foodRating, float serviceRating, int userId, String comment, Date date) {
        this.foodRating = foodRating;
        this.serviceRating = serviceRating;
        this.userId = userId;
        this.comment = comment;
        this.date = date;
        this.restaurantId = resturantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(int userAvatar) {
        this.userAvatar = userAvatar;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public float getFoodRating() {
        return foodRating;
    }

    public void setFoodRating(float foodRating) {
        this.foodRating = foodRating;
    }

    public float getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(float serviceRatingl) {
        this.serviceRating = serviceRatingl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getResturantId() {
        return restaurantId;
    }

    public void setResturantId(int resturantId) {
        this.restaurantId = resturantId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }
}
