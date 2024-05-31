package com.example.travellabapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travellabapplication.ui.main.TravelDetailsFragment

class TravelDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_details)
        val travelId = intent.getStringExtra("travelId") // Odbieranie ID podróży

        if (savedInstanceState == null) {
            val fragment = TravelDetailsFragment.newInstance()
            fragment.arguments = Bundle().apply {
                putString("travelId", travelId)
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }
}
