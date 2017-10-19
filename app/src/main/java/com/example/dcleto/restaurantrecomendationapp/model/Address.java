package com.example.dcleto.restaurantrecomendationapp.model;

/**
 * Created by Daniel on 13/07/2017.
 */

public class Address {
    private double latitude;
    private double longitude;
    private String street;
    private String city;
    private String country;

    public Address() {
    }

    public Address(double[] coords) {
        this.latitude = coords[0];
        this.longitude = coords[1];
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
