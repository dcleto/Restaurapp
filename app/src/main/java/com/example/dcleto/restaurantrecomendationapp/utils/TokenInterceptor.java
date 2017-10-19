package com.example.dcleto.restaurantrecomendationapp.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Daniel on 13/07/2017.
 */

public class TokenInterceptor implements Interceptor {
    private String token;

    public TokenInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (this.token != null) {
            Request.Builder requestBuilder = request.newBuilder()
                    .addHeader("Authorization", "bearer "+this.token);
            Request newRequest = requestBuilder.build();

            return chain.proceed(newRequest);
        }
        return chain.proceed(request);

    }
}
