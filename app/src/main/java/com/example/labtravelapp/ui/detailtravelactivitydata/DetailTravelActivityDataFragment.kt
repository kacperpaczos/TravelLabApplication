package com.example.labtravelapp.ui.detailtravelactivitydata

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labtravelapp.R
import com.example.labtravelapp.AppDatabase
import com.example.labtravelapp.Trip

class DetailTravelActivityDataFragment : Fragment() {

    companion object {
        fun newInstance(tripId: Int) = DetailTravelActivityDataFragment().apply {
            arguments = Bundle().apply {
                putInt("TRIP_ID", tripId)
            }
        }
    }

    private val viewModel: DetailTravelActivityDataViewModel by viewModels()
    private lateinit var db: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TripDataAdapter

    fun setDatabase(database: AppDatabase) {
        db = database
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_travel_activity_data, container, false)
        val tripId = arguments?.getInt("TRIP_ID") ?: -1

        // Załaduj dane wycieczki za pomocą tripId
        viewModel.setDatabase(db)
        viewModel.loadTrip(tripId)

        // Znajdź RecyclerView i ustaw adapter
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        adapter = TripDataAdapter(requireContext(), Trip(
            id = 0,
            name = "",
            description = "",
            date = "",
            startLocation = "",
            endLocation = "",
            estimatedDistance = 0.0,
            estimatedTime = 0.0,
            cost = 0.0,
            rating = 0.0f,
            guide = ""
        ))
        recyclerView.adapter = adapter

        viewModel.trip.observe(viewLifecycleOwner, Observer { data ->
            adapter.data = data ?: Trip(
                id = 0,
                name = "",
                description = "",
                date = "",
                startLocation = "",
                endLocation = "",
                estimatedDistance = 0.0,
                estimatedTime = 0.0,
                cost = 0.0,
                rating = 0.0f,
                guide = ""
            )
            adapter.notifyDataSetChanged()
        })

        return view
    }
}
