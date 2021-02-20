package com.example.flixster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.GlideApp;
import com.example.flixster.R;
import com.example.flixster.models.Movie;


import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the movie at the position
        Movie movie = movies.get(position);
        //Bind the movie data into the ViewHolder
        holder.bind(movie);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ImageView fullImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            fullImage = itemView.findViewById(R.id.fullImage);
        }

        public void bind(Movie movie) {
            String imgUrl;
            if(movie.getRatingValue() > 5.0) {
                Log.i("FiveStarMovie:", String.valueOf(movie.getRatingValue()));
                Log.i("FiveStarMovie:", movie.getTitle());
                imgUrl = movie.getBackdropPath();
                Glide.with(context).load(imgUrl).into(fullImage);
            } else {

                tvTitle.setText(movie.getTitle());
                tvOverview.setText(movie.getOverview());

                //if the phone is in landscape mode
                if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    imgUrl = movie.getBackdropPath();
                } else {
                    imgUrl = movie.getPosterPath();
                }
                Glide.with(context).load(imgUrl).into(ivPoster);
            }


//            GlideApp.with(context)
//                    .load("http://via.placeholder.com/300.png")
//                    .placeholder(R.drawable.ic_launcher_background)
//                    .error(R.drawable.ic_launcher_foreground)
//                    .into(ivPoster);
        }
    }
}
