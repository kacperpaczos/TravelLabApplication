package com.example.travellabapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.travellabapplication.models.Travel
import com.example.travellabapplication.viewmodels.TravelViewModel

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

        val viewModel = ViewModelProvider(this).get(TravelViewModel::class.java)
        viewModel.insertTravels(
            Travel(
                id = "1",
                title = "Stary Rynek",
                description = "Zabytkowy rynek w centrum Poznania.",
                distanceKm = 2.0f,
                startLocation = "Plac Wolności",
                endLocation = "Stary Rynek",
                rating = 4.5f,
                suggestedTime = "2 godziny",
                curiosities = "Codziennie w południe koziołki trykają się na wieży ratuszowej."
            ),
            Travel(
                id = "2",
                title = "Katedra Poznańska",
                description = "Najstarsza katedra w Polsce.",
                distanceKm = 1.0f,
                startLocation = "Stary Rynek",
                endLocation = "Ostrów Tumski",
                rating = 4.7f,
                suggestedTime = "1 godzina",
                curiosities = "Znajduje się tu grobowiec pierwszych władców Polski."
            ),
            Travel(
                id = "3",
                title = "Zamek Cesarski",
                description = "Zamek zbudowany dla cesarza Wilhelma II.",
                distanceKm = 1.5f,
                startLocation = "Stary Rynek",
                endLocation = "Zamek Cesarski",
                rating = 4.6f,
                suggestedTime = "1.5 godziny",
                curiosities = "Obecnie mieści się tu Centrum Kultury Zamek."
            )
        )
    }
}
