package com.example.dcleto.restaurantrecomendationapp.services;

import com.example.dcleto.restaurantrecomendationapp.model.AuthResponse;
import com.example.dcleto.restaurantrecomendationapp.model.Review;
import com.example.dcleto.restaurantrecomendationapp.model.User;
import com.example.dcleto.restaurantrecomendationapp.model.UserCredentials;
import com.example.dcleto.restaurantrecomendationapp.model.UserRegistrationResponse;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

import retrofit2.http.GET;

/**
 * Created by Daniel on 11/07/2017.
 */

public interface AuthService {

    @FormUrlEncoded
    @POST("oauth2/token")
    Observable<AuthResponse> Get(@Field("username") String username, @Field("password") String password, @Field("grant_type") String grant_type);

    @POST("account/register")
    Observable<UserRegistrationResponse> signUp(@Body User user);
}
