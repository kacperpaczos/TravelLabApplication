package com.example.labtravelapp.ui.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch
import com.example.labtravelapp.AppDatabase
import com.example.labtravelapp.DetailTravelActivity
import com.example.labtravelapp.R
import com.example.labtravelapp.Trip

class TripFragment : Fragment() {

    private var columnCount = 1
    private lateinit var tripAdapter: MyTripRecyclerViewAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trip_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.list)

        recyclerView?.apply {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            tripAdapter = MyTripRecyclerViewAdapter(emptyList()) { trip ->
                val intent = Intent(context, DetailTravelActivity::class.java).apply {
                    putExtra("TRIP_ID", trip.id)
                }
                startActivity(intent)
            }
            adapter = tripAdapter
        }

        loadTrips()
    }

    private fun loadTrips() {
        lifecycleScope.launch {
            val db = Room.databaseBuilder(
                requireContext(),
                AppDatabase::class.java, "trips-database"
            ).build()
            val trips = db.tripDao().getAllTrips()
            tripAdapter.updateTrips(trips)
        }
    }
}
