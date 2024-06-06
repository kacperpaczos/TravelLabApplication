package com.example.labtravelapp.ui.detailtravelactivitydata

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.labtravelapp.R
import com.example.labtravelapp.Trip

class TripDataAdapter(private val context: Context, var data: List<Trip>) : RecyclerView.Adapter<TripDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_trip, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trip = data[position]
        holder.nameTextView.text = trip.name
        holder.descriptionTextView.text = trip.description
        holder.costTextView.text = trip.cost.toString()
        holder.ratingTextView.text = trip.rating.toString()
        holder.guideTextView.text = trip.guide
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.trip_name)
        val descriptionTextView: TextView = view.findViewById(R.id.trip_description)
        val costTextView: TextView = view.findViewById(R.id.trip_cost)
        val ratingTextView: TextView = view.findViewById(R.id.trip_rating)
        val guideTextView: TextView = view.findViewById(R.id.trip_guide)
    }
}
