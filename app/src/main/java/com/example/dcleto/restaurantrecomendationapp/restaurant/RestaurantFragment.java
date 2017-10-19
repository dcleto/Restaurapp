package com.example.dcleto.restaurantrecomendationapp.restaurant;


import android.Manifest;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcleto.restaurantrecomendationapp.Constants;
import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.model.Review;
import com.example.dcleto.restaurantrecomendationapp.reviews.ReviewsActivity;
import com.example.dcleto.restaurantrecomendationapp.reviews.ReviewsRecyclerAdapter;
import com.example.dcleto.restaurantrecomendationapp.utils.PicassoCache;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantFragment extends Fragment implements RestaurantContract.View {

    Toolbar mToolbar;
    TextView mName, mDescription, mDirection, mRestType, mPriceRange;
    ImageView mImage, mLogo;
    RatingBar mRating;
    LinearLayout mReviews;
    RestaurantContract.Presenter mPresenter;
    FloatingActionButton mFab;
    CollapsingToolbarLayout mCollapsingToolbar;
    AlertDialog mRateDialog = null;

    public RestaurantFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);
        rootView.findViewById(R.id.btn_add_review).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.openAddReview();
            }
        });
        mFab = rootView.findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.callRestaurant();
            }
        });
        rootView.findViewById(R.id.btn_reviews).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.openReviews();
            }
        });

        mImage = rootView.findViewById(R.id.image);
        mLogo = rootView.findViewById(R.id.iv_logo);
        mName = rootView.findViewById(R.id.tv_name);
        mRating = rootView.findViewById(R.id.main_rating);
        mDescription = rootView.findViewById(R.id.tv_description);
        mDirection = rootView.findViewById(R.id.tv_direction);
        mRestType = rootView.findViewById(R.id.tv_rest_type);
        mPriceRange = rootView.findViewById(R.id.tv_price_range);
        mReviews = rootView.findViewById(R.id.reviews);
        mToolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        mCollapsingToolbar = rootView.findViewById(R.id.toolbar_layout);
        getActivity().supportPostponeEnterTransition();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

    }

    @Override
    public void showRestaurant(Restaurant restaurant) {
        // mCollapsingToolbar.setTitle(restaurant.getName());
        mImage.setImageResource(restaurant.getImage());
        mName.setText(restaurant.getName());
        mRating.setRating((float) restaurant.getRating());

        //mDescription.setText(restaurant.get);,mDirection,mRestType,mPriceRange
        String direction = restaurant.getAddress().getCity() + " " + restaurant.getAddress().getCountry();
        mDirection.setText(direction);
        mRestType.setText(restaurant.getRestaurantType().getName());
        mPriceRange.setText(restaurant.getMinPrice() + " - " + restaurant.getMaxPrice());
        PicassoCache.getPicassoInstance(getActivity())
                .load(Constants.END_POINT + restaurant.getLogo()).into(mLogo);
        //PicassoCache.getPicassoInstance(getActivity()).load(Constants.END_POINT+restaurant.getPhoto()).into(mImage);
        int count = Math.min(restaurant.getReviews().size(),3);
        mReviews.removeAllViews();
        for (int i = 0; i < count; i++) {
            View review = LayoutInflater.from(getActivity()).inflate(R.layout.item_review2,null);
            ReviewsRecyclerAdapter.ReviewsVH vh = new ReviewsRecyclerAdapter.ReviewsVH(review);
            vh.setData(restaurant.getReviews().get(i));
            mReviews.addView(review);
        }

        PicassoCache.getPicassoInstance(getActivity())
                .load(Constants.END_POINT + restaurant.getPhoto())
                .fit()
                .noFade()
                .centerCrop()
                .into(mImage, new Callback() {
                    @Override
                    public void onSuccess() {
                        getActivity().supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError() {
                        getActivity().supportStartPostponedEnterTransition();
                    }
                });

    }

    @Override
    public void setPresenter(RestaurantContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showReviews(String restaurantId) {
        Intent intent = new Intent(getActivity(), ReviewsActivity.class);
        intent.putExtra("RESTAURANT_ID", restaurantId);
        startActivity(intent);
    }

    @Override
    public void showReviewDialog() {
        if (mRateDialog != null) {
            mRateDialog.show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.rate_dialog, null);
        RatingBar foodRating = view.findViewById(R.id.rating_food);
        RatingBar serviceRating = view.findViewById(R.id.rating_service);
        EditText opinion = view.findViewById(R.id.et_opinion);
        view.findViewById(R.id.btn_rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRateDialog.dismiss();
            }
        });
        builder.setView(view);
        mRateDialog = builder.create();
        mRateDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                mPresenter.addReview(foodRating.getRating(), serviceRating.getRating(), opinion.getText().toString());
            }
        });
        mRateDialog.show();
    }

    @Override
    public void showNewReview(Review review) {
        View reviewView = LayoutInflater.from(getActivity()).inflate(R.layout.item_review2,null);
        ReviewsRecyclerAdapter.ReviewsVH vh = new ReviewsRecyclerAdapter.ReviewsVH(reviewView);
        vh.setData(review);
        reviewView.setAlpha(0);

        mReviews.addView(reviewView,0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(reviewView,"alpha",0f,1f);
        animator.setInterpolator(new AccelerateInterpolator());
         animator.setDuration(500);
        animator.start();
    }

    @Override
    public void call(String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        getActivity().startActivity(intent);
    }


}
