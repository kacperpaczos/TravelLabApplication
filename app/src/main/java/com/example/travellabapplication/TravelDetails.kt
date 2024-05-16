package com.example.travellabapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.travellabapplication.ui.main.TravelDetailsFragment

class TravelDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_details)
        val buttonNumber = intent.getIntExtra("buttonNumber", -1) // Odbieranie numeru przycisku

        if (savedInstanceState == null) {
            val fragment = TravelDetailsFragment.newInstance()
            fragment.arguments = Bundle().apply {
                putInt("buttonNumber", buttonNumber)
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
        }
    }
}