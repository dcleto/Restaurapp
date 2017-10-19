package com.example.dcleto.restaurantrecomendationapp.model;

import java.util.List;

/**
 * Created by Daniel on 13/07/2017.
 */

public class Restaurant {
    private int id;
    private String name;
    private double rating;
    private int image;
    private String logo;
    private String photo;
    private RestaurantType restaurantType;
    private FoodType foodType;
    private Address address;
    private double minPrice;
    private double maxPrice;
    private String phoneNumber;
    private List<Review> reviews;

    public int restaurantTypeId;
    public int foodTypeId;
    private float distanceKm;

    public Restaurant(){}
    public Restaurant(int id,String name, double rating, RestaurantType restaurantType, FoodType foodType, Address address ,int image, String logo) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.restaurantType = restaurantType;
        this.foodType = foodType;
        this.address = address;
        this.image = image;
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public RestaurantType getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(RestaurantType restaurantType) {
        this.restaurantType = restaurantType;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRestaurantTypeId() {
        return restaurantTypeId;
    }

    public void setRestaurantTypeId(int restaurantTypeId) {
        this.restaurantTypeId = restaurantTypeId;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public float getDistance() {

        return distanceKm;
    }
}
