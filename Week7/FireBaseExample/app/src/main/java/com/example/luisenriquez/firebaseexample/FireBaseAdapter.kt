package com.example.luisenriquez.firebaseexample

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.luisenriquez.firebaseexample.databinding.ItemMessageBinding

class FireBaseAdapter (var chatMessages: ArrayList<Message>) :
        RecyclerView.Adapter<FireBaseAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder
    {
        val binding =
                DataBindingUtil.inflate<ItemMessageBinding>(LayoutInflater.from(p0.context),
                        R.layout.item_message, p0, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int
    {
        return chatMessages.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int)
    {
        var temp = chatMessages[p1]

        p0.binding.apply {
            tvMessage.text = temp.text
            tvName.text = temp.name
        }
    }


    class ViewHolder(var binding: ItemMessageBinding) :
            RecyclerView.ViewHolder(binding.root)
}
