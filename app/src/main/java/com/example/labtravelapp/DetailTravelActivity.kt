package com.example.labtravelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.labtravelapp.ui.detailtravelactivitydata.DetailTravelActivityDataFragment
import com.example.labtravelapp.ui.detailtravelactivitystoper.DetailTravelActivityStoperFragment

class DetailTravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_travel_activity_layout)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val tripId = intent.getIntExtra("TRIP_ID", -1)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, DetailTravelActivityStoperFragment.newInstance("param1", "param2"))
                .replace(R.id.container2, DetailTravelActivityDataFragment.newInstance())
                .commitNow()
        }

        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}