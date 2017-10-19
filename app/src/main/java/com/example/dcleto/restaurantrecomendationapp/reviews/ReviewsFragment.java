package com.example.dcleto.restaurantrecomendationapp.reviews;


import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.model.Review;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReviewsFragment extends Fragment implements ReviewsContract.View {

    ReviewsContract.Presenter mPresenter;
    RecyclerView mRecycler;
    ReviewsRecyclerAdapter mAdapter;
    public ReviewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reviews, container, false);
        mRecycler = (RecyclerView) rootView.findViewById(R.id.reviews_recycler);
        mAdapter = new ReviewsRecyclerAdapter(null);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        mRecycler.setAdapter(mAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        mRecycler.addItemDecoration(dividerItemDecoration);
        return rootView;
    }

    @Override
    public void showReviews(List<Review> reviews) {
        mAdapter.setData(reviews);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();

    }

    @Override
    public void setPresenter(ReviewsContract.Presenter presenter) {
        mPresenter =presenter;
    }
}
