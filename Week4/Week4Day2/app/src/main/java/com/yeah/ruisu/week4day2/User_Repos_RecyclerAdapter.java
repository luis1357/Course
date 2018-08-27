package com.yeah.ruisu.week4day2;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.yeah.ruisu.week4day2.data.remote.models.ReposDatum;
import com.yeah.ruisu.week4day2.databinding.ReposItemBinding;

import java.util.List;

public class User_Repos_RecyclerAdapter
        extends RecyclerView.Adapter<User_Repos_RecyclerAdapter.ViewHolder>
{
    private static final String TAG ="Adapter: ";

    private Context context;
    private List<ReposDatum> reposList;

    public User_Repos_RecyclerAdapter(Context context, List<ReposDatum> reposList)
    {
        this.context = context;
        this.reposList = reposList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");


        ReposItemBinding myBinder =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.repos_item,
                        parent,
                        false);
        return new ViewHolder(myBinder);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        ReposDatum temp = reposList.get(position);

        holder.MyBinder
                .tvRepoName
                .setText(temp.getName());
        holder.MyBinder
                .tvRepoLanguage
                .setText(temp.getLanguage().toString());
        holder.MyBinder
                .tvRepoLink
                .setText(temp.getUrl());
    }

    @Override
    public int getItemCount()
    {
        return reposList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ReposItemBinding MyBinder;

        public ViewHolder(ReposItemBinding InBinder)
        {
            super(InBinder.getRoot());
            this.MyBinder = InBinder;
        }


    }

}
