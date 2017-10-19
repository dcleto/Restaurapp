package com.example.dcleto.restaurantrecomendationapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.dcleto.restaurantrecomendationapp.authentication.login.LoginActivity;
import com.example.dcleto.restaurantrecomendationapp.model.User;
import com.example.dcleto.restaurantrecomendationapp.utils.TokenUtil;

/**
 * Created by Daniel on 11/08/2017.
 */

public class SessionManager {
    private User user;
    private Context mContext;
    private Location mCurrentLocation;
    protected static SessionManager instance;

    private SessionManager(Context context) {
        mContext = context;


    }
    public static SessionManager getInstance(Context context) {
        if(instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public  User getUser() {
        return user;
    }

    public  void setUser(User user) {
        this.user = user;
    }

    public void logout(){
        TokenUtil.setToken(mContext,null);
        Intent intent = new Intent(mContext, LoginActivity.class);
        setUser(null);
        mContext.startActivity(intent);
    }

    public Location getCurrentLocation() {
        return mCurrentLocation;
    }

}
