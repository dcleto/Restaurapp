package com.example.dcleto.restaurantrecomendationapp.restaurants;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dcleto.restaurantrecomendationapp.Constants;
import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.model.Restaurant;
import com.example.dcleto.restaurantrecomendationapp.utils.PicassoCache;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Daniel on 29/07/2017.
 */

public class RestaurantsRecyclerAdapter extends RecyclerView.Adapter<RestaurantsRecyclerAdapter.ViewHolder> {
    List<Restaurant> mData;
    OnRestaurantClickListener mListener;

    public RestaurantsRecyclerAdapter(List<Restaurant> data,OnRestaurantClickListener listener) {
        this.mData = data;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_restaurant,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurant restaurant = mData.get(position);
        holder.setData(restaurant);
    }

    @Override
    public int getItemCount() {
        if(mData == null) return 0;
        return mData.size();
    }

    public void setData(List<Restaurant> restaurants) {
        mData = restaurants;
        notifyDataSetChanged();
    }

    public void setOnRestaurantClickListener(OnRestaurantClickListener onRestaurantClickListener) {
        this.mListener = onRestaurantClickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView tv_name, tv_location, tv_distance;
        RatingBar rating;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_location = itemView.findViewById(R.id.tv_location);
            rating = (RatingBar) itemView.findViewById(R.id.main_rating);
            itemView.setOnClickListener(this);
        }

        public void setData(Restaurant restaurant){
            tv_name.setText(restaurant.getName());
            rating.setRating((float)restaurant.getRating());
            tv_distance.setText(restaurant.getDistance() + " Km");
            PicassoCache.getPicassoInstance(image.getContext()).load(Constants.END_POINT+restaurant.getPhoto())
                    .fit()
                    .centerCrop()
                    .into(image);

        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(mData.get(getPosition()), image);
        }
    }

    public interface OnRestaurantClickListener {
        void onItemClick(Restaurant item,ImageView view);
    }

}
