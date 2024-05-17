package com.example.travellabapplication

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.travellabapplication.ui.theme.TravelLabApplicationTheme
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.travellabapplication.viewmodels.TravelViewModel
import com.example.travellabapplication.viewmodels.Travel
import androidx.compose.runtime.getValue

class MainActivity : ComponentActivity() {
    private lateinit var travelViewModel: TravelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        travelViewModel = ViewModelProvider(this).get(TravelViewModel::class.java)
        setContent {
            TravelLabApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TravelListScreen(viewModel = travelViewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }

        // Ustaw dane podróży
        travelViewModel.setTravelData(
            id = "1",
            title = "Nazwa podróży",
            description = "Opis podróży",
            distanceKm = 100,
            startLocation = "Początek podróży",
            endLocation = "Koniec podróży",
            rating = 4.5f
        )
    }
}

@Composable
fun TravelListScreen(viewModel: TravelViewModel, modifier: Modifier = Modifier) {
    val travelList: List<Travel> by viewModel.travelData.collectAsState(initial = emptyList())

    Column(modifier = modifier.fillMaxWidth()) {
        travelList.forEach { trip ->
            TravelItem(trip = trip)
        }
    }
}

@Composable
fun TravelItem(trip: Travel) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, TravelDetails::class.java).apply {
                putExtra("travelId", trip.id)
            }
            context.startActivity(intent)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(text = "Tytuł: ${trip.title}")
            Text(text = "Opis: ${trip.description}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TravelListScreenPreview() {
    TravelLabApplicationTheme {
        TravelListScreen(viewModel = TravelViewModel())
    }
}