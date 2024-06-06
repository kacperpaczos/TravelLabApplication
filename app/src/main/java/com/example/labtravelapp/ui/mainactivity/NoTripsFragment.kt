package com.example.labtravelapp.ui.mainactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.labtravelapp.AppDatabase
import com.example.labtravelapp.R
import com.example.labtravelapp.Trip
import kotlinx.coroutines.launch

class NoTripsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_no_trips, container, false)

        val db = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDatabase::class.java, "trips-database"
        ).build()

        view.findViewById<Button>(R.id.fill_button).setOnClickListener {
            lifecycleScope.launch {
                val tripDao = db.tripDao()
                val trips = listOf(
                    Trip(name = "Wycieczka 1", description = "Opis 1", date = "2023-10-01", startLocation = "Start 1", endLocation = "End 1", estimatedDistance = 10.0, estimatedTime = 2.0),
                    Trip(name = "Wycieczka 2", description = "Opis 2", date = "2023-10-02", startLocation = "Start 2", endLocation = "End 2", estimatedDistance = 20.0, estimatedTime = 4.0),
                    Trip(name = "Wycieczka 3", description = "Opis 3", date = "2023-10-03", startLocation = "Start 3", endLocation = "End 3", estimatedDistance = 15.0, estimatedTime = 3.0),
                    Trip(name = "Wycieczka 4", description = "Opis 4", date = "2023-10-04", startLocation = "Start 4", endLocation = "End 4", estimatedDistance = 25.0, estimatedTime = 5.0),
                    Trip(name = "Wycieczka 5", description = "Opis 5", date = "2023-10-05", startLocation = "Start 5", endLocation = "End 5", estimatedDistance = 30.0, estimatedTime = 6.0)
                )
                trips.forEach { trip ->
                    tripDao.insert(trip)
                }
                // Po dodaniu wycieczek, przeładuj aplikację, aby pokazać listę wycieczek
                requireActivity().recreate()
            }
        }

        return view
    }
}