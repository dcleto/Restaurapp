package com.example.dcleto.restaurantrecomendationapp.restaurants;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.example.dcleto.restaurantrecomendationapp.SessionManager;
import com.example.dcleto.restaurantrecomendationapp.data.RestaurantsRepository;
import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.utils.TokenUtil;

import java.util.List;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Daniel on 23/07/2017.
 */

public class RestaurantsPresenter implements RestaurantsContract.Presenter {
    RestaurantsContract.View mView;
    RestaurantsRepository mRepo;
    List<Restaurant> mRestaurants;
    LocationManager mLocationManager;
    Location mCurrentLocation;
    Context mContext;

    public RestaurantsPresenter(RestaurantsContract.View view, RestaurantsRepository repo) {
        mView = view;
        mRepo = repo;
        mView.setPresenter(this);
    }


    public RestaurantsPresenter(RestaurantsContract.View view, RestaurantsRepository repo, Context context) {
        mView = view;
        mRepo = repo;
        mView.setPresenter(this);
        mContext = context;
        mLocationManager =  (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    public void start() {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
       return;
         }
        mCurrentLocation  = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(mCurrentLocation == null)
            mCurrentLocation  = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if(mCurrentLocation != null)
         mRepo.get(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude())
                 .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v->{
                    mRestaurants = v;
                    mView.showRestaurants(v,mCurrentLocation);
                }, e->{


                });
    }

    @Override
    public void setView(RestaurantsContract.View view) {
        mView = view;
        mView.setPresenter(this);
        if(mRestaurants != null){
            mView.showRestaurants(mRestaurants,mCurrentLocation);
        }
    }

    @Override
    public void openRestaurantDetails(Restaurant restaurant) {
        String id = restaurant.getId() +"";
        mView.showRestaurantDetail(id);
    }

    @Override
    public void refresh() {
        mRepo.get(mCurrentLocation.getLatitude(),mCurrentLocation.getLongitude()).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v->{
                    mRestaurants = v;
                    mView.showRestaurants(v,mCurrentLocation);
                    mView.hideRefreshing();
                }, e->{


                });
    }
}
