package com.example.travellabapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travellabapplication.models.Travel

class TravelAdapter(private val onClick: (Travel) -> Unit) :
    RecyclerView.Adapter<TravelAdapter.TravelViewHolder>() {

    private var travelList: List<Travel> = emptyList()

    fun submitList(list: List<Travel>) {
        travelList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_travel, parent, false)
        return TravelViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: TravelViewHolder, position: Int) {
        holder.bind(travelList[position])
    }

    override fun getItemCount(): Int = travelList.size

    class TravelViewHolder(itemView: View, val onClick: (Travel) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        private var currentTravel: Travel? = null

        init {
            itemView.setOnClickListener {
                currentTravel?.let {
                    onClick(it)
                }
            }
        }

        fun bind(travel: Travel) {
            currentTravel = travel
            titleTextView.text = travel.title
            descriptionTextView.text = travel.description
        }
    }
}
