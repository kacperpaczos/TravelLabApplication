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
import kotlinx.coroutines.launch
import android.util.Log
import com.example.labtravelapp.Trip
import com.example.labtravelapp.TripStatus
import com.example.labtravelapp.utils.FileUtils

class NoTripsFragment : Fragment() {
    private val TAG = "NoTripsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            val view = inflater.inflate(R.layout.fragment_no_trips, container, false)

            val db = Room.databaseBuilder(
                requireContext().applicationContext,
                AppDatabase::class.java, "trips-database"
            ).build()

            view.findViewById<Button>(R.id.fill_button).setOnClickListener {
                lifecycleScope.launch {
                    try {
                        val tripDao = db.tripDao()
                        val trips = FileUtils.loadTripsFromAssets(requireContext())
                        trips.forEach { trip ->
                            tripDao.insert(trip)
                        }
                        Log.d(TAG, "Wycieczki dodane pomyślnie")
                        requireActivity().recreate()
                    } catch (e: Exception) {
                        Log.e(TAG, "Błąd podczas dodawania wycieczek", e)
                    }
                }
            }

            Log.d(TAG, "onCreateView: Widok utworzony pomyślnie")
            return view
        } catch (e: Exception) {
            Log.e(TAG, "onCreateView: Błąd podczas tworzenia widoku", e)
            return null
        }
    }
}