package com.example.dcleto.restaurantrecomendationapp.reviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dcleto.restaurantrecomendationapp.Constants;
import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.data.InMemoryRestaurantRepository;
import com.example.dcleto.restaurantrecomendationapp.data.RemoteRestaurantRepository;
import com.example.dcleto.restaurantrecomendationapp.restaurant.RestaurantContract;
import com.example.dcleto.restaurantrecomendationapp.restaurant.RestaurantFragment;
import com.example.dcleto.restaurantrecomendationapp.restaurant.RestaurantPresenter;
import com.example.dcleto.restaurantrecomendationapp.utils.TokenUtil;

public class ReviewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        String restaurantId = getIntent().getStringExtra("RESTAURANT_ID");
        ReviewsFragment fragment = new ReviewsFragment();
        ReviewsContract.Presenter presenter = new ReviewsPresenter(fragment,new RemoteRestaurantRepository(Constants.END_POINT+"api/", TokenUtil.getToken(this)),restaurantId);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container,fragment)
                .commit();
    }
}
