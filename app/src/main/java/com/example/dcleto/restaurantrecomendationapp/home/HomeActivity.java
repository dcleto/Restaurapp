package com.example.dcleto.restaurantrecomendationapp.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcleto.restaurantrecomendationapp.Constants;
import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.SessionManager;
import com.example.dcleto.restaurantrecomendationapp.authentication.login.LoginActivity;
import com.example.dcleto.restaurantrecomendationapp.data.RemoteRestaurantRepository;
import com.example.dcleto.restaurantrecomendationapp.data.RestaurantsRepository;
import com.example.dcleto.restaurantrecomendationapp.model.User;
import com.example.dcleto.restaurantrecomendationapp.restaurants.RestaurantListFragment;
import com.example.dcleto.restaurantrecomendationapp.restaurants.RestaurantsContract;
import com.example.dcleto.restaurantrecomendationapp.restaurants.RestaurantsMapFragment;
import com.example.dcleto.restaurantrecomendationapp.restaurants.RestaurantsPresenter;
import com.example.dcleto.restaurantrecomendationapp.utils.PicassoCache;
import com.example.dcleto.restaurantrecomendationapp.utils.TokenUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager mViewPager;
    RestaurantsFragmentAdapter mRestaurantsAdapter;
    TabLayout mTabs;
    SessionManager mSessionManager;
    ImageView mHeaderImage;
    TextView mHeaderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView =(NavigationView) findViewById(R.id.nav_view);
        View header =  navigationView.inflateHeaderView(R.layout.nav_header_home);
        navigationView.setNavigationItemSelectedListener(this);
        mHeaderText =  header.findViewById(R.id.tv_user_name);
        mHeaderImage =  header.findViewById(R.id.iv_user_avatar);
        List<Fragment> fragments = new ArrayList<>();

        RestaurantsMapFragment mapFragment = new RestaurantsMapFragment();
        RestaurantListFragment listFragment = new RestaurantListFragment();
        fragments.add(mapFragment);
        fragments.add(listFragment);

        mSessionManager = SessionManager.getInstance(this);



        RestaurantsRepository repo = new RemoteRestaurantRepository(Constants.END_POINT+"api/",TokenUtil.getToken(this));
        RestaurantsContract.Presenter presenter = new RestaurantsPresenter(mapFragment,repo,this);
        if(mSessionManager.getUser() != null  )
            setUser(mSessionManager.getUser());
        else
        repo.getUser().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u->{
                    mSessionManager.setUser(u);
                    setUser(u);
                 }, e->{
                    Toast.makeText(HomeActivity.this,e.getMessage(),Toast.LENGTH_SHORT);

                });



        mRestaurantsAdapter = new RestaurantsFragmentAdapter(getSupportFragmentManager(), fragments);

        mTabs = (TabLayout) findViewById(R.id.tabs);
        mViewPager =(ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mRestaurantsAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RestaurantsContract.View  view = (RestaurantsContract.View ) fragments.get(position);
                presenter.setView(view);
             }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabs.setupWithViewPager(mViewPager);

        mTabs.getTabAt(0).setIcon(R.drawable.ic_map_white_24dp);
        mTabs.getTabAt(1).setIcon(R.drawable.ic_format_list_bulleted_white_24dp);
        mTabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.tabSelected));
        String token = TokenUtil.getToken(this);
        if(token == null || token.equals("")){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void setUser(User u) {
        mHeaderText.setText(u.getFirstName() + " " + u.getLastName());
        if(u.getAvatar() == null)
            mHeaderImage.setImageResource(R.drawable.default_user);
        else
        PicassoCache.getPicassoInstance(mHeaderImage.getContext()).load(Constants.END_POINT+u.getAvatar())
                .fit()
                .centerCrop()
                .into(mHeaderImage);
    }


    private void makeUseOfNewLocation(Location location) {

        return;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int id = item.getItemId();

        if (id == R.id.nav_exit) {
            mSessionManager.logout();
        }
        else{
            Snackbar.make(drawer,"Coming Soon",Snackbar.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
