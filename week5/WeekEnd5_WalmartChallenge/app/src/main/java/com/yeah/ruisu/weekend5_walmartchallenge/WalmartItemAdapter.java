package com.yeah.ruisu.weekend5_walmartchallenge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.yeah.ruisu.weekend5_walmartchallenge.data.remote.models.Item;
import com.yeah.ruisu.weekend5_walmartchallenge.databinding.ResultItemBinding;

import java.util.List;

public class WalmartItemAdapter
    extends RecyclerView.Adapter<WalmartItemAdapter.ViewHolder>
{
    private static final String TAG = "Item Adapter: ";

    private Context myContext;
    private List<Item> itemList;

    public WalmartItemAdapter(Context context, List<Item> itemList)
    {
        this.myContext = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        Log.d(TAG, "onCreateViewHolder: ");

        ResultItemBinding myBinder =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                                                            R.layout.result_item,
                                                            viewGroup,
                                                            false);
        return new ViewHolder(myBinder);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        Item temp = itemList.get(i);

        viewHolder.myBinder
                    .tvItmNm
                    .setText(temp.getName());

        viewHolder.myBinder
                    .tvItmPrc
                    .setText(temp.getSalePrice().toString());
        String imageUrl = temp.getMediumImage();
        Glide.with(myContext).load(imageUrl).into(viewHolder.myBinder.ivItm);
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        ResultItemBinding myBinder;

        public ViewHolder(ResultItemBinding inBinder)
        {
            super(inBinder.getRoot());
            this.myBinder = inBinder;
        }
    }
}
