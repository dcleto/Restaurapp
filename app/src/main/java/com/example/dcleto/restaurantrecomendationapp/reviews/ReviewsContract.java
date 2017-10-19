package com.example.dcleto.restaurantrecomendationapp.reviews;

import com.example.dcleto.restaurantrecomendationapp.model.Review;

import java.util.List;

/**
 * Created by daniel cleto on 8/8/2017.
 */

public class ReviewsContract {

    public interface View{
        void showReviews(List<Review> reviews);
        void setPresenter(Presenter presenter);
    }

    public interface Presenter{
        void start();
    }
}
