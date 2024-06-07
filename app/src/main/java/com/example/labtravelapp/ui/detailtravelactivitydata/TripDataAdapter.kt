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
            "<b>${context.getString(R.string.trip_name_text)}</b>: ${data.name}",
            "<b>${context.getString(R.string.trip_description_text)}</b>: ${data.description}",
            "<b>${context.getString(R.string.trip_cost_text)}</b>: ${data.cost}",
            "<b>${context.getString(R.string.trip_rating_text)}</b>: ${data.rating}",
            "<b>${context.getString(R.string.trip_guide_text)}</b>: ${data.guide}"
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_trip_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.detailTextView.text = android.text.Html.fromHtml(tripDetails[position])
    }

    override fun getItemCount(): Int = tripDetails.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val detailTextView: TextView = view.findViewById(R.id.trip_detail)
    }
}
