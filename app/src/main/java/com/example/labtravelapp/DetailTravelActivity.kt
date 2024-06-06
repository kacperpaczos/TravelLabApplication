package com.example.labtravelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.labtravelapp.ui.detailtravelactivitydata.DetailTravelActivityDataFragment
import com.example.labtravelapp.ui.detailtravelactivitystoper.DetailTravelActivityStoperFragment
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.labtravelapp.ui.detailtravelactivitydata.DetailTravelActivityDataViewModel

class DetailTravelActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailTravelActivityDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_travel_activity_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val tripId = intent.getIntExtra("TRIP_ID", -1)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "trips-database"
        ).build()

        if (savedInstanceState == null) {
            val dataFragment = DetailTravelActivityDataFragment.newInstance(tripId)
            dataFragment.setDatabase(db)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, DetailTravelActivityStoperFragment())
                .replace(R.id.container2, dataFragment)
                .commitNow()
        }

        viewModel = ViewModelProvider(this).get(DetailTravelActivityDataViewModel::class.java)
        viewModel.setDatabase(db)
        viewModel.loadTrip(tripId)

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}