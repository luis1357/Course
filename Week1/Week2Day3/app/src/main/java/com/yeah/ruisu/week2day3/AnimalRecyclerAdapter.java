package com.yeah.ruisu.week2day3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AnimalRecyclerAdapter
    extends RecyclerView.Adapter<AnimalRecyclerAdapter.ViewHolder>
{
    private static final String TAG = "Adapter";

    List<Animal> AnimalLst = new ArrayList<>();

    public AnimalRecyclerAdapter (List<Animal> InAnimalLst)
    {
        AnimalLst = InAnimalLst;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");

        View MyView = LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.animal_recycler_item, null);
        return new ViewHolder(MyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");

        Animal TempAnimal = AnimalLst.get(position);

        if(TempAnimal != null)
        {
            holder.tvAnimalName.setText(TempAnimal.Name);
            holder.ivAnimalPic.setImageResource(TempAnimal.Picture);
        }
    }

    @Override
    public int getItemCount()
    {
        return AnimalLst.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView ivAnimalPic;
        private final TextView tvAnimalName;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.ivAnimalPic = itemView.findViewById(R.id.ivAnimalPic);
            this.tvAnimalName = itemView.findViewById(R.id.tvAnimalName);
        }


    }
}
