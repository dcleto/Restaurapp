package com.example.dcleto.restaurantrecomendationapp.model;

/**
 * Created by Daniel on 13/07/2017.
 */

public class RestaurantType {
    private int id;
    private String name;
    private String description;

    public RestaurantType(String name) {
        this.name = name;
    }

    public RestaurantType() {
        this.name = name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}