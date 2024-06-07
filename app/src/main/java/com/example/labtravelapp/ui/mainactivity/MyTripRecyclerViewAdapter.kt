package com.example.labtravelapp.ui.mainactivity

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.labtravelapp.R

import com.example.labtravelapp.Trip
import com.example.labtravelapp.databinding.FragmentTripListMainActivityBinding
import com.google.android.material.button.MaterialButton

class MyTripRecyclerViewAdapter(
    private var values: List<Trip>,
    private val onClickListener: (Trip) -> Unit
) : RecyclerView.Adapter<MyTripRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentTripListMainActivityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.name
        holder.itemView.setOnClickListener { onClickListener(item) }
        holder.detailButton.setOnClickListener { onClickListener(item) }
    }

    override fun getItemCount(): Int = values.size

    fun updateTrips(newTrips: List<Trip>) {
        values = newTrips
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentTripListMainActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content
        val detailButton: MaterialButton = binding.detailButton

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}