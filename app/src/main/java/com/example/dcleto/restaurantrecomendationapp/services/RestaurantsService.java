package com.example.dcleto.restaurantrecomendationapp.services;

import com.example.dcleto.restaurantrecomendationapp.model.AuthResponse;
import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.model.Review;
import com.example.dcleto.restaurantrecomendationapp.model.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Daniel on 13/07/2017.
 */

public interface RestaurantsService {
    @GET("restaurants")
    Observable<List<Restaurant>> Get();

    @GET("userrecomendation")
    Observable<List<Restaurant>> Get(@Query("lat") double lat,@Query("lon") double lon);

    @GET("restaurants/{id}")
    Observable<Restaurant> Get(@Path("id") int id);

    @GET("reviews")
    Observable<List<Review>> GetReviews(@Query("restaurantId") int restaurantId);

    @GET("users")
    Observable<User> GetUser();

    @POST("reviews")
    Observable<Review> addReview(@Body Review review);
}
