package com.example.dcleto.restaurantrecomendationapp.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dcleto.restaurantrecomendationapp.restaurants.RestaurantsMapFragment;

import java.util.List;

/**
 * Created by Daniel on 29/07/2017.
 */

public class RestaurantsFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments;
    public RestaurantsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public RestaurantsFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
       return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if(mFragments == null) return 0;
        return mFragments.size();
    }

}
