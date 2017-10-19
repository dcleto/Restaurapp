package com.example.dcleto.restaurantrecomendationapp.reviews;

import com.example.dcleto.restaurantrecomendationapp.data.RestaurantsRepository;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by daniel cleto on 8/8/2017.
 */

public class ReviewsPresenter implements ReviewsContract.Presenter {
    private ReviewsContract.View mView;
    private RestaurantsRepository mRepo;
    private String mRestaurantId;

    public ReviewsPresenter(ReviewsContract.View mView, RestaurantsRepository mRepo, String restaurantId) {
        this.mView = mView;
        this.mRepo = mRepo;
        this.mRestaurantId = restaurantId;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        int reviewId = Integer.parseInt(mRestaurantId);
        mRepo.getReviews(reviewId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(r->{
                    mView.showReviews(r);
                });
    }
}
