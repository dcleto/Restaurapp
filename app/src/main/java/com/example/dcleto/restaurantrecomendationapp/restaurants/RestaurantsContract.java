package com.example.dcleto.restaurantrecomendationapp.restaurants;

import android.location.Location;

import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;

import java.util.List;

/**
 * Created by Daniel on 23/07/2017.
 */

public class   RestaurantsContract {
    public interface View{
        void showRestaurants(List<Restaurant> restaurants, Location location);
        void setPresenter(Presenter presenter);
        void showRestaurantDetail(String restaurantId);
        void setCurrentLocation(Location location);
        void hideRefreshing();
    }
    public interface Presenter{
        void start();
        void setView(View view);
        void openRestaurantDetails(Restaurant restaurant);
        void refresh();
    }

}
