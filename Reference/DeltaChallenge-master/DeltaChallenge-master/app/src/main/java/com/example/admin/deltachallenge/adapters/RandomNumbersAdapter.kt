package com.example.admin.deltachallenge.adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.admin.deltachallenge.R
import com.example.admin.deltachallenge.databinding.RecyclerItemBinding
import com.example.admin.deltachallenge.ui.CommunicatorViewModel

class RandomNumbersAdapter(private val randomNumbers: List<Int>,
                           private val communicatorViewModel: CommunicatorViewModel) : RecyclerView.Adapter<RandomNumbersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomNumbersAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<RecyclerItemBinding>(LayoutInflater.from(parent.context),
                R.layout.recycler_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = randomNumbers.size

    override fun onBindViewHolder(holder: RandomNumbersAdapter.ViewHolder, position: Int) {
        Log.d("Binding", randomNumbers[position].toString())
        holder.binding.number.text = randomNumbers[position].toString()
        holder.binding.number.setOnClickListener {
            communicatorViewModel.pickedNumber.value = (it as TextView).text.toString().toInt()
        }
    }

    fun getItem(position: Int) = randomNumbers[position]

    class ViewHolder(var binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)

}