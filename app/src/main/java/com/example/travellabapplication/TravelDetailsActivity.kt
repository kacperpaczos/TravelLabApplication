package com.example.travellabapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.commit

class TravelDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_details)

        if (savedInstanceState == null) {
            val travelId = intent.getStringExtra("travelId")
            supportFragmentManager.commit {
                replace(R.id.fragment_container, TravelDetailsFragment.newInstance(travelId))
            }
        }
    }
}