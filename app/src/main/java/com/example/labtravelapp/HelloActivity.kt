package com.example.labtravelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.labtravelapp.ui.mainactivity.MainActivity
import android.util.Log
import android.os.Handler
import android.os.Looper

class HelloActivity : AppCompatActivity() {
    private val TAG = "HelloActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            // Hide the action bar
            supportActionBar?.hide()
            
            val splashScreen = installSplashScreen()
            
            // Keep splash screen visible for animation duration
            var keepSplashScreenOn = true
            splashScreen.setKeepOnScreenCondition { keepSplashScreenOn }
            
            // Add a delay to show the animation
            Handler(Looper.getMainLooper()).postDelayed({
                keepSplashScreenOn = false
            }, 1500) // Match this with animation duration
            
            setContentView(R.layout.activity_hello)
            
            Log.d(TAG, "onCreate: Ekran powitalny zainstalowany")

            val toursButton = findViewById<Button>(R.id.toursButton)
            toursButton.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            Log.e(TAG, "onCreate: Error", e)
        }
    }
} 