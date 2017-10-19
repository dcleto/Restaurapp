package com.example.dcleto.restaurantrecomendationapp.restaurant;

import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.model.Review;
import com.example.dcleto.restaurantrecomendationapp.restaurants.RestaurantsContract;

/**
 * Created by Daniel on 30/07/2017.
 */

public class RestaurantContract {
    public interface View{
        void showRestaurant(Restaurant restaurant);
        void setPresenter(Presenter presenter);
        void showReviews(String restaurantId);
        void showReviewDialog();
        void showNewReview(Review review);
        void call(String phoneNumber);

    }

    public  interface Presenter{
        void start();
        void openReviews();
        void addReview(float foodRating, float serviceRating,String opinion);
        void openAddReview();
        void callRestaurant();

    }
}
