package com.example.dcleto.restaurantrecomendationapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Daniel on 13/07/2017.
 */

public class TokenUtil {
    public static String getToken(Context context){
       SharedPreferences preferences = context.getSharedPreferences("app",Context.MODE_PRIVATE);
       String token = preferences.getString("Token","");
    return token;}

    public static void setToken(Context context,String token){
        SharedPreferences.Editor prefEditor = context.getSharedPreferences("app",Context.MODE_PRIVATE).edit();
        prefEditor.putString("Token",token);
        prefEditor.commit();
    }

}
