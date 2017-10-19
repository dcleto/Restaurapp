package com.example.dcleto.restaurantrecomendationapp.restaurants;


import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.data.InMemoryRestaurantRepository;
import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.restaurant.RestaurantActivity;

import java.util.List;

public class RestaurantListFragment extends Fragment implements RestaurantsContract.View {

    RestaurantsContract.Presenter mPresenter;
    RecyclerView mRecyclerView;
    RestaurantsRecyclerAdapter mAdapter;
    SwipeRefreshLayout swipeContainer;

    public RestaurantListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_restaurant_list, container, false);
        mRecyclerView =  rootView.findViewById(R.id.restaurants_recycler);
        mAdapter = new RestaurantsRecyclerAdapter(null, null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
        swipeContainer = rootView.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });

        return rootView;
    }

    private ImageView mCurrentImage;
    private RestaurantsRecyclerAdapter.OnRestaurantClickListener clickListener = new RestaurantsRecyclerAdapter.OnRestaurantClickListener() {
        @Override
        public void onItemClick(Restaurant item, ImageView view) {
            mCurrentImage = view;
            mPresenter.openRestaurantDetails(item);
        }
    };

    @Override
    public void showRestaurants(List<Restaurant> restaurants, Location location) {
        mAdapter.setData(restaurants);
    }

    @Override
    public void setPresenter(RestaurantsContract.Presenter presenter) {
        mPresenter = presenter;
        mAdapter.setOnRestaurantClickListener(clickListener);
    }

    @Override
    public void showRestaurantDetail(String restaurantId) {
        ActivityOptionsCompat anim = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),mCurrentImage,"targetImage");
        Intent intent = new Intent(getActivity(), RestaurantActivity.class);
        intent.putExtra("RESTAURANT_ID",restaurantId);
        startActivity(intent,anim.toBundle());
    }

    @Override
    public void setCurrentLocation(Location location) {

    }

    @Override
    public void hideRefreshing() {
        swipeContainer.setRefreshing(false);
    }
}
