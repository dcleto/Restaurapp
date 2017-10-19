package com.example.dcleto.restaurantrecomendationapp.data;

import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.model.Address;
import com.example.dcleto.restaurantrecomendationapp.model.FoodType;
import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.model.RestaurantType;
import com.example.dcleto.restaurantrecomendationapp.model.Review;
import com.example.dcleto.restaurantrecomendationapp.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Observable;

/**
 * Created by Daniel on 23/07/2017.
 */

public class InMemoryRestaurantRepository implements RestaurantsRepository {

    //length 7
    private double[][] address = new double[][]{{18.465283, -69.908174},{18.464476, -69.910138},{18.462635281,-69.909460544},{18.462859167,-69.906306266},{18.464955537,-69.906134605},{18.462553868,-69.909996986},{18.465179419,-69.904868602}};

    private  RestaurantType[] restTypes = new RestaurantType[]{new RestaurantType("Mexican"),new RestaurantType("Italian"),new RestaurantType("American"),new RestaurantType("Chinese"),new RestaurantType("Thai"),new RestaurantType("Japanese")};
    private  FoodType[] foodTypes = new FoodType[]{new FoodType("Pizza"),new FoodType("Burgers"),new FoodType("Spicy"),new FoodType("IceCream"),new FoodType("Vegan"),new FoodType("Meat")};
    List<Restaurant> restaurants = new ArrayList<>();
    public InMemoryRestaurantRepository() {
//        Restaurant restaurant = new Restaurant(1,"McDonalds",3,restTypes[0], foodTypes[0],new Address(address[0]), R.drawable.rest1, R.drawable.logo_mc);
//        Restaurant restaurant2 = new Restaurant(2,"Wendys",4,restTypes[1], foodTypes[1],new Address(address[1]),R.drawable.rest2,R.drawable.logo_wendys);
//        Restaurant restaurant3 = new Restaurant(3,"Burger King",4,restTypes[2], foodTypes[2],new Address(address[2]),R.drawable.rest3,R.drawable.logo_burgerking);
//        Restaurant restaurant4 = new Restaurant(4,"StarBucks",5,restTypes[3], foodTypes[3],new Address(address[3]),R.drawable.rest4,R.drawable.logo_starbucks);
//        Restaurant restaurant5 = new Restaurant(5,"KFC",1,restTypes[4], foodTypes[4],new Address(address[4]),R.drawable.rest1,R.drawable.logo_kfc);
//        Restaurant restaurant6 = new Restaurant(6,"McDonalds",5,restTypes[5], foodTypes[5],new Address(address[5]),R.drawable.rest2,R.drawable.logo_mc);
//        restaurants.add(restaurant);
//        restaurants.add(restaurant2);
//        restaurants.add(restaurant3);
//        restaurants.add(restaurant4);
//        restaurants.add(restaurant5);
//        restaurants.add(restaurant6);
    }

    @Override
    public Observable<List<Restaurant>> get() {
        return Observable.just(restaurants);
    }

    @Override
    public Observable<List<Restaurant>> get( double lat, double lon) {
        return null;
    }

    @Override
    public Observable<Restaurant> get(int id) {
         for (int i = 0; i < restaurants.size(); i++) {
            if(restaurants.get(i).getId() == id)
            return Observable.just(restaurants.get(i));
        }
        return null;
    }

    @Override
    public Observable<List<Review>> getReviews(int restaurantId) {
        List<Review> reviews = new ArrayList<>();
        Review review1 = new Review("Pedro",new Date(),R.drawable.user1,3.5f,"La comida estaba regular");
        Review review2 = new Review("Jose",new Date(),R.drawable.user2,4.5f,"Lo mejor que he probado en toda mi vida");
        Review review3 = new Review("Martin",new Date(),R.drawable.user3,2.5f,"La comida estaba fria");
        Review review4 = new Review("Alberto",new Date(),R.drawable.user4,2.5f,"La comida estaba tan fresca que se movia:(");
        Review review5 = new Review("Ana",new Date(),R.drawable.user5,5f,"Simplemente lo mejor");

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);
        return Observable.just(reviews);
    }

    @Override
    public Observable<Review> addReview(Review review) {
        return null;
    }

    @Override
    public Observable<User> getUser() {
        return null;
    }
}
