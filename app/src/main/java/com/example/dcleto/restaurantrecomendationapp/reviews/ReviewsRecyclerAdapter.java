package com.example.dcleto.restaurantrecomendationapp.reviews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dcleto.restaurantrecomendationapp.Constants;
import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.model.Review;
import com.example.dcleto.restaurantrecomendationapp.utils.PicassoCache;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by daniel cleto on 8/8/2017.
 */

public class ReviewsRecyclerAdapter extends RecyclerView.Adapter<ReviewsRecyclerAdapter.ReviewsVH> {
    List<Review> mData;

    public ReviewsRecyclerAdapter(List<Review> mData) {
        this.mData = mData;
    }

    @Override
    public ReviewsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review,parent,false);
        return new ReviewsVH(view);
    }

    @Override
    public void onBindViewHolder(ReviewsVH holder, int position) {
        Review review = mData.get(position);
        holder.setData(review);
    }

    @Override
    public int getItemCount() {
        return mData !=null? mData.size() : 0 ;
    }

    public void setData(List<Review> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    public static class ReviewsVH extends RecyclerView.ViewHolder{
        ImageView avatar;
        RatingBar rating;
        TextView comment;
        TextView userName;
        TextView date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        public ReviewsVH(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.iv_avatar);
            rating = itemView.findViewById(R.id.main_rating);
            comment = itemView.findViewById(R.id.tv_comment);
            userName = itemView.findViewById(R.id.tv_userName);
            date = itemView.findViewById(R.id.tv_date);
        }

        public void setData(Review review) {
           // avatar.setImageResource(R.drawable.default_user);
            rating.setRating(review.getRating());
            comment.setText(review.getComment());
            String name = review.getUserName() == null? "Anonimo" : review.getUserName();
            userName.setText(name);
            date.setText("08/08/2017");
            String av = Constants.END_POINT+review.getAvatar();
            if(review.getAvatar() == null)
                avatar.setImageResource(R.drawable.default_user);
            else
            PicassoCache.getPicassoInstance(avatar.getContext()).load(av)
                    .fit()
                    .centerCrop()
                    .into(avatar);
           // date.setText(dateFormat.format(review.getDate()));
         }
    }
}
