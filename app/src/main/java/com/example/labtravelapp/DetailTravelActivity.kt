package com.example.labtravelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.util.Log
import com.example.labtravelapp.ui.detailtravelactivitydata.DetailTravelActivityDataFragment
import com.example.labtravelapp.ui.detailtravelactivitystoper.DetailTravelActivityStoperFragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.labtravelapp.ui.detailtravelactivitydata.DetailTravelActivityDataViewModel
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.graphics.Color

class DetailTravelActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailTravelActivityDataViewModel
    private val TAG = "DetailTravelActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ustawienie przezroczystego paska statusu
        window.statusBarColor = Color.TRANSPARENT
        Log.d(TAG, "DetailTravelActivity: onCreate rozpoczęty")
        try {
            setContentView(R.layout.detail_travel_activity_layout)
            Log.d(TAG, "DetailTravelActivity: Layout załadowany pomyślnie")
            
            // Dodaj Toolbar
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val tripId = intent.getIntExtra("TRIP_ID", -1)
            Log.d(TAG, "Otrzymane ID wycieczki: $tripId")

            if (tripId == -1) {
                Log.e(TAG, "Nieprawidłowe ID wycieczki")
                Toast.makeText(this, "Błąd podczas ładowania wycieczki", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "trips-database"
            ).build()

            viewModel = ViewModelProvider(this)[DetailTravelActivityDataViewModel::class.java]
            viewModel.setDatabase(db)
            viewModel.loadTrip(tripId)
            Log.d(TAG, "ViewModel and database initialized")

            if (savedInstanceState == null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container1, DetailTravelActivityStoperFragment())
                    .replace(R.id.container2, DetailTravelActivityDataFragment())
                    .commitNow()
                Log.d(TAG, "DetailTravelActivity: Fragmenty dodane pomyślnie")
            }

            val backButton = findViewById<FloatingActionButton>(R.id.back_button)
            backButton?.setOnClickListener {
                finish()
            }

            Log.d(TAG, "DetailTravelActivity: Aktywność utworzona pomyślnie z ID wycieczki: $tripId")
        } catch (e: Exception) {
            Log.e(TAG, "DetailTravelActivity: Błąd podczas tworzenia aktywności", e)
            Toast.makeText(this, "Wystąpił błąd podczas ładowania szczegółów", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
