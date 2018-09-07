package com.example.luisenriquez.week6day4.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.luisenriquez.week6day4.R;
import com.example.luisenriquez.week6day4.data.remote.models.MovieResponse;
import com.example.luisenriquez.week6day4.databinding.MovieRecyclerItemBinding;
import com.example.luisenriquez.week6day4.utils.Constants;


public class MovieRecyclerAdapter
        extends RecyclerView.Adapter<MovieRecyclerAdapter.ViewHolder>
{
    private static final String TAG ="Adapter: ";

    private Context context;
    private MovieResponse movieList;

    public MovieRecyclerAdapter(Context context, MovieResponse movieList)
    {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");


        MovieRecyclerItemBinding myBinder =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.movie_recycler_item,
                        parent,
                        false);
        return new ViewHolder(myBinder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        MovieResponse temp = movieList;

        holder.myBinder
                .tvMvTitle
                .setText(temp.getResults().get(position).getTitle());

        holder.myBinder
                .tvMvDate
                .setText(temp.getResults().get(position).getReleaseDate());

        String imageUrl = Constants.MOVIE_IMAGE_BASE_URL +
                            temp.getResults().get(position).getPosterPath();

        holder.myBinder
                .rbMvRating
                .setRating(Float.parseFloat(temp.getResults()
                                            .get(position).getVoteAverage().toString()));

        Glide.with(context).load(imageUrl).into(holder.myBinder.ivMvImg);

    }

    @Override
    public int getItemCount()
    {
        return movieList.getResults().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        MovieRecyclerItemBinding myBinder;

        public ViewHolder(MovieRecyclerItemBinding inBinder)
        {
            super(inBinder.getRoot());
            this.myBinder = inBinder;
        }


    }
}
