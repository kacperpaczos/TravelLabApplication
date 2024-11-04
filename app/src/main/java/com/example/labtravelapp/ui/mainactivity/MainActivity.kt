package com.example.labtravelapp.ui.mainactivity

import com.example.labtravelapp.TravelApplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.labtravelapp.AppDatabase
import com.example.labtravelapp.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import android.os.Handler
import android.os.Looper
import android.widget.FrameLayout
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var animationFinished = false
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: start")

        try {

            setContentView(R.layout.activity_main)
            Log.d(TAG, "onCreate: layout set - Resources configuration: ${resources.configuration}")

            database = (application as TravelApplication).database
            Log.d(TAG, "onCreate: database initialized")

            setupViewPager()
            Log.d(TAG, "onCreate: viewPager setup complete")

            setupTabLayout()
            Log.d(TAG, "onCreate: tabLayout setup complete")

            setupUI()
            Log.d(TAG, "onCreate: UI setup complete")

            // Test bazy danych
            val db = (application as TravelApplication).database
            Log.d("MainActivity", "Database instance: $db")

            // Opcjonalnie - test zapytania w coroutine scope
            lifecycleScope.launch {
                val tripCount = db.tripDao().getAllTrips().size
                Log.d("MainActivity", "Number of trips in database: $tripCount")
                
                if (tripCount == 0) {
                    // Show NoTripsFragment when there are no trips
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, NoTripsFragment())
                        .commitNow()
                }
            }

            lifecycleScope.launch {
                val allTrips = database.tripDao().getAllTrips()
                Log.d(TAG, "Total trips in database: ${allTrips.size}")
                allTrips.forEach { trip ->
                    Log.d(TAG, "Trip: ${trip.name}, Status: ${trip.status}")
                }
            }

            // Set up toolbar
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
        } catch (e: Exception) {
            Log.e(TAG, "onCreate: Error", e)
        }
    }

    private fun setupViewPager() {
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = TripsPagerAdapter(this)
        // Set offscreen page limit to improve performance
        viewPager.offscreenPageLimit = 1
    }

    private fun setupTabLayout() {
        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.completed_trips)
                1 -> getString(R.string.ongoing_trips)
                2 -> getString(R.string.planned_trips)
                else -> null
            }
        }.attach()
    }

    private fun setupUI() {
        try {
            val fab = findViewById<FloatingActionButton>(R.id.fab_send_sms)
            Log.d(TAG, "setupUI: FAB found: ${fab != null}")
            
            fab?.setOnClickListener {
                Log.d(TAG, "FAB clicked")
                sendSmsGreeting()
            }
            
            val exitButton = findViewById<Button>(R.id.exit_button)
            Log.d(TAG, "setupUI: Exit button found: ${exitButton != null}")
            
            exitButton?.setOnClickListener {
                Log.d(TAG, "Exit button clicked")
                finish()
            }
        } catch (e: Exception) {
            Log.e(TAG, "setupUI: Error", e)
        }
    }

    private suspend fun initializeApp() {
        try {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "trips-database"
            ).build()

            val tripDao = db.tripDao()
            val tripsFromDb = tripDao.getAllTrips()
            withContext(Dispatchers.Main) {
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
                Log.d("MainActivity", "Przycisk wyjścia został naciśnięty. Zamykanie aplikacji.")
                finish()
            }
        } catch (e: Exception) {
            Log.e(TAG, "initializeApp: Błąd podczas inicjalizacji aplikacji", e)
        }
    }

    private fun sendSmsGreeting() {
        val phoneNumber = "123456789" // Numer telefonu odbiorcy
        val message = "Pozdrowienia z mojej podróży!"

        try {
            val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$phoneNumber")
                putExtra("sms_body", message)
            }
            startActivity(smsIntent)
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas wysyłania SMS", e)
            Toast.makeText(this, "Nie można wysłać SMS", Toast.LENGTH_SHORT).show()
        }
    }
}