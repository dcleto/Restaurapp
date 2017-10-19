package com.example.dcleto.restaurantrecomendationapp.restaurant;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dcleto.restaurantrecomendationapp.Constants;
import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.SessionManager;
import com.example.dcleto.restaurantrecomendationapp.data.InMemoryRestaurantRepository;
import com.example.dcleto.restaurantrecomendationapp.data.RemoteRestaurantRepository;
import com.example.dcleto.restaurantrecomendationapp.utils.TokenUtil;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        String restaurantId = getIntent().getStringExtra("RESTAURANT_ID");
        RestaurantFragment fragment = new RestaurantFragment();
        RestaurantContract.Presenter presenter = new RestaurantPresenter(restaurantId, fragment,new RemoteRestaurantRepository(Constants.END_POINT+"api/", TokenUtil.getToken(this)), SessionManager.getInstance(this));

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container,fragment)
                .commit();


    }
}
