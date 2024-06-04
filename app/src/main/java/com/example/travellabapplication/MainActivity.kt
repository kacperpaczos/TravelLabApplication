package com.example.travellabapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, TravelListFragment.newInstance())
            }
        }

        val buttonExit: Button = findViewById(R.id.button_exit)
        buttonExit.setOnClickListener {
            finishAffinity() // Zamyka aplikację
        }
    }
}
