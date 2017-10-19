package com.example.dcleto.restaurantrecomendationapp.data;

import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.model.Review;
import com.example.dcleto.restaurantrecomendationapp.model.User;
import com.example.dcleto.restaurantrecomendationapp.services.RestaurantsService;
import com.example.dcleto.restaurantrecomendationapp.utils.DateDeserializer;
import com.example.dcleto.restaurantrecomendationapp.utils.TokenInterceptor;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Daniel on 09/08/2017.
 */

public class RemoteRestaurantRepository implements RestaurantsRepository {

    RestaurantsService mService;

    public RemoteRestaurantRepository(String baseUrl,String token) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new TokenInterceptor(token)).build();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();

         mService = retrofit.create(RestaurantsService.class);
    }

    @Override
    public Observable<List<Restaurant>> get() {
        return mService.Get();
    }

    @Override
    public Observable<List<Restaurant>> get( double lat, double lon) {
        return mService.Get(lat,lon);
    }

    @Override
    public Observable<Restaurant> get(int id) {

        return mService.Get(id);
    }

    @Override
    public Observable<List<Review>> getReviews(int restaurantId) {
        return mService.GetReviews(restaurantId);
    }

    @Override
    public Observable<Review> addReview(Review review) {
        return mService.addReview(review);
    }

    @Override
    public Observable<User> getUser() {
        return mService.GetUser();
    }
}
