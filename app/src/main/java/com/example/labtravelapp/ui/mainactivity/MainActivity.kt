package com.example.labtravelapp.ui.mainactivity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.labtravelapp.AppDatabase
import com.example.labtravelapp.R
import com.example.labtravelapp.Trip
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "trips-database"
        ).build()

        lifecycleScope.launch {
            val tripDao = db.tripDao()
            val tripsFromDb = tripDao.getAllTrips()
            if (tripsFromDb.isEmpty()) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, NoTripsFragment())
                    .commitNow()
            } else {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, TripFragment())
                    .commitNow()
            }
        }

        findViewById<Button>(R.id.exit_button).setOnClickListener {
            finish()
        }
    }
}