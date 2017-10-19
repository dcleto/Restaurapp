package com.example.dcleto.restaurantrecomendationapp.restaurant;

import android.util.Log;

import com.example.dcleto.restaurantrecomendationapp.SessionManager;
import com.example.dcleto.restaurantrecomendationapp.data.InMemoryRestaurantRepository;
import com.example.dcleto.restaurantrecomendationapp.data.RestaurantsRepository;
import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.model.Review;
import com.example.dcleto.restaurantrecomendationapp.restaurants.RestaurantsContract;

import java.util.Date;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Daniel on 30/07/2017.
 */

public class RestaurantPresenter implements RestaurantContract.Presenter {
    RestaurantContract.View mView;
    RestaurantsRepository mRepo;
    SessionManager mSessionManager;
    String mPhoneNumber;
    int mRestaurantId;

    public RestaurantPresenter(String restaurantId, RestaurantFragment view, RestaurantsRepository repo, SessionManager sessionManager) {
        this.mView = view;
        this.mRepo = repo;
        this.mRestaurantId = Integer.parseInt(restaurantId);
        this.mSessionManager = sessionManager;
         mView.setPresenter(this);
    }

    @Override
    public void start() {
        mRepo.get(mRestaurantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(r->{
                    mPhoneNumber = r.getPhoneNumber();
                    mView.showRestaurant(r);
                });
    }

    @Override
    public void openReviews() {
        mView.showReviews(String.valueOf(mRestaurantId));
    }

    @Override
    public void addReview(float foodRating, float serviceRating, String opinion) {
        int userId =mSessionManager.getUser().getId();
        if(foodRating == 0 && serviceRating ==0) return;
        Review review = new Review(mRestaurantId, foodRating,serviceRating,userId,opinion, new Date());
        mRepo.addReview(review)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(review1 -> {
                    mView.showNewReview(review1);
                }, error->{
                    Log.e("ERROR",error.getMessage());
                });
    }

    @Override
    public void openAddReview() {
        mView.showReviewDialog();
    }

    @Override
    public void callRestaurant() {
        mView.call(mPhoneNumber);
    }

}
