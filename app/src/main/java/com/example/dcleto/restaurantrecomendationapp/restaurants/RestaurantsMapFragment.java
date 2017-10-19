package com.example.dcleto.restaurantrecomendationapp.restaurants;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dcleto.restaurantrecomendationapp.Constants;
import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.restaurant.RestaurantActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Daniel on 23/07/2017.
 */

public class RestaurantsMapFragment extends SupportMapFragment implements RestaurantsContract.View, OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener {
    RestaurantsContract.Presenter mPresenter;
    GoogleMap mMap;
    View popup;
    String mBaseUrl = Constants.END_POINT;
    int mCurrentrestCount = 0;
    @Override
    public void showRestaurants(List<Restaurant> restaurants, Location location) {

        mMap.clear();
        setCurrentLocation(location);
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(new LatLng(location.getLatitude(), location.getLongitude()));

        mCurrentrestCount = restaurants.size();
        for (Restaurant restaurant : restaurants) {
            FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.item_marker,null);
            layout.setDrawingCacheEnabled(true);
            ImageView image = layout.findViewById(R.id.marker_icon);
            Picasso.with(getActivity()).load(mBaseUrl+restaurant.getLogo()).into(image, new Callback() {
                @Override
                public void onSuccess() {
                    layout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                    layout.layout(0, 0, layout.getMeasuredWidth(), layout.getMeasuredHeight());

                    layout.buildDrawingCache();
                    Bitmap bm = layout.getDrawingCache();


                    MarkerOptions options = new  MarkerOptions()
                            .position(new LatLng(restaurant.getAddress().getLatitude(), restaurant.getAddress().getLongitude()))
                            .title(restaurant.getName())
                            .icon(BitmapDescriptorFactory.fromBitmap(bm));
                    Marker marker = mMap.addMarker(options);
                    ;
                    builder.include(new LatLng(restaurant.getAddress().getLatitude(), restaurant.getAddress().getLongitude()));
                    marker.setTag(restaurant);
                    mCurrentrestCount--;
                    if(mCurrentrestCount == 0){
                        animateInBounds(builder);
                    }
                 }

                @Override
                public void onError() {

                }
            });
        }
       // animateInBounds(builder);

    }

    void animateInBounds(LatLngBounds.Builder builder){
        LatLngBounds bounds = builder.build();
        int padding = 140;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.animateCamera(cu);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.getMapAsync(this);

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    public void setPresenter(RestaurantsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showRestaurantDetail(String restaurantId) {
        Intent intent = new Intent(getActivity(), RestaurantActivity.class);
        intent.putExtra("RESTAURANT_ID",restaurantId);
        startActivity(intent);
    }

    @Override
    public void setCurrentLocation(Location location) {
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.blue_circle);

        MarkerOptions options = new  MarkerOptions()
                .position(new LatLng(location.getLatitude(),location.getLongitude()))
                .title("Mi Ubicacion")
                .zIndex(100f)
                .icon(BitmapDescriptorFactory.fromBitmap(icon));

        Marker marker = mMap.addMarker(options);
    }

    @Override
    public void hideRefreshing() {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                if(popup == null){
                    popup = LayoutInflater.from(getActivity()).inflate(R.layout.restaurant_popup,null);
                    popup.setTag(new InfoWindowVH(popup));
                }
                Restaurant restaurant =(Restaurant) marker.getTag();
                if(restaurant == null) return  null;
                InfoWindowVH vh = (InfoWindowVH) popup.getTag();
                vh.setRestaurant(restaurant);
                return popup;
            }
        });
        mMap.setOnInfoWindowClickListener(this);
        mPresenter.start();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Restaurant restaurant = (Restaurant) marker.getTag();
        mPresenter.openRestaurantDetails( restaurant);
    }

    private class InfoWindowVH{
        RatingBar ratingBar;
        TextView title;
        TextView type;
        ImageView logo;
        InfoWindowVH(View view){
            ratingBar  = (RatingBar) view.findViewById(R.id.ratingBar);
            title = (TextView) view.findViewById(R.id.tv_title);
            logo = view.findViewById(R.id.logo_restaurant);
            type = view.findViewById(R.id.tv_rest_type);
        }
        void setRestaurant(Restaurant restaurant){
            if(restaurant == null) return;;
            ratingBar.setRating((float)restaurant.getRating());
            title.setText(restaurant.getName());
            type.setText(restaurant.getRestaurantType().getName());
            Picasso.with(getActivity()).load(mBaseUrl+restaurant.getLogo()).into(logo);
        }
    }
}
