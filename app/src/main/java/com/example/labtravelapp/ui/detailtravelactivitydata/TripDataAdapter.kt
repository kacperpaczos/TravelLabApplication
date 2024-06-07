package com.example.labtravelapp.ui.detailtravelactivitydata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labtravelapp.R
import com.example.labtravelapp.Trip

class TripDataAdapter(private val context: Context, var data: Trip) : RecyclerView.Adapter<TripDataAdapter.ViewHolder>() {

    private val tripDetails: List<String>
        get() = listOf(
            "Name: ${data.name}",
            "Description: ${data.description}",
            "Cost: ${data.cost}",
            "Rating: ${data.rating}",
            "Guide: ${data.guide}"
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_trip_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.detailTextView.text = tripDetails[position]
    }

    override fun getItemCount(): Int = tripDetails.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailTextView: TextView = view.findViewById(R.id.trip_detail)
    }
}
