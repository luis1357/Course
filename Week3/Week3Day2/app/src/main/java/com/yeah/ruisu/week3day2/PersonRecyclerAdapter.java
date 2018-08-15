package com.yeah.ruisu.week3day2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersonRecyclerAdapter
        extends RecyclerView.Adapter<PersonRecyclerAdapter.ViewHolder>
{
    private static final String TAG ="Adapter";

    List<Person> PersonList = new ArrayList<>();

    public PersonRecyclerAdapter(List<Person> PersonList)
    {
        this.PersonList = PersonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");
        View MyView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_recycler_item, null);

        return new ViewHolder(MyView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "onBindViewHolder: ");

        Person PersonListTemp = PersonList.get(position);

        if(PersonListTemp != null)
        {
            holder.tvPName.setText(PersonListTemp.Name);
            holder.tvPAge.setText(PersonListTemp.Age);
            holder.tvPGender.setText(PersonListTemp.Gender);
            holder.tvPNation.setText(PersonListTemp.Nationality);
        }
    }

    @Override
    public int getItemCount()
    {
        return PersonList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvPName;
        private final TextView tvPAge;
        private final TextView tvPGender;
        private final TextView tvPNation;

        public ViewHolder(View itemView)
        {
            super(itemView);
            this.tvPName = itemView.findViewById(R.id.tvPNameRecycler);
            this.tvPAge = itemView.findViewById(R.id.tvPAgeRecycler);
            this.tvPGender = itemView.findViewById(R.id.tvPGenderRecycler);
            this.tvPNation = itemView.findViewById(R.id.tvPNationRecycler);
        }


    }
}
