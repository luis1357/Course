package com.example.luisenriquez.week7day4test.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.renderscript.ScriptGroup
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.luisenriquez.week7day4test.R
import com.example.luisenriquez.week7day4test.data.remote.model.NumbersResponse
import com.example.luisenriquez.week7day4test.databinding.MyRecyclerItemBinding
import com.example.luisenriquez.week7day4test.utils.Constants

class NumberRecyclerAdapter (private val numberRList: NumbersResponse,
                             private val context: Context)
    : RecyclerView.Adapter<NumberRecyclerAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val itemBinding = DataBindingUtil.inflate<MyRecyclerItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.my_recycler_item,
                parent,
                false)

        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = numberRList.getData()?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        var temp = numberRList.getData()!![position]

        holder.binding.tvNumber.text = temp.toString()
    }


    class ViewHolder(var binding: MyRecyclerItemBinding ) : RecyclerView.ViewHolder(binding.root)
}