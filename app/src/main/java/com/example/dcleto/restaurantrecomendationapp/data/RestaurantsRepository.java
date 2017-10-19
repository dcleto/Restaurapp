package com.example.dcleto.restaurantrecomendationapp.data;

import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.model.Review;
import com.example.dcleto.restaurantrecomendationapp.model.User;

import java.util.List;

import rx.Observable;

/**
 * Created by Daniel on 23/07/2017.
 */

public interface RestaurantsRepository {
    Observable<List<Restaurant>> get();
    Observable<List<Restaurant>> get(double lat,double lon);
    Observable<Restaurant> get(int id);
    Observable<List<Review>> getReviews(int restaurantId);
    Observable< Review> addReview(Review review);
    Observable<User> getUser();

}
