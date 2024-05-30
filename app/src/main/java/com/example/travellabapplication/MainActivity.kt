package com.example.travellabapplication

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
import androidx.compose.ui.res.stringResource
import com.example.travellabapplication.R
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class MainActivity : ComponentActivity() {
    private lateinit var travelViewModel: TravelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        travelViewModel = ViewModelProvider(this).get(TravelViewModel::class.java)
        setContent {
            TravelLabApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(onClick = { finish() }) {
                            Icon(Icons.Filled.ExitToApp, contentDescription = "Wyjście")
                        }
                    }
                ) { innerPadding ->
                    TravelListScreen(viewModel = travelViewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }

        // Ustaw dane podróży
        travelViewModel.setTravelData(
            listOf(
                Travel(
                    id = "1",
                    title = "Wycieczka po Starym Rynku",
                    description = "Odwiedź Ratusz, Fontannę Prozerpiny i Kościół Farny.",
                    distanceKm = 2,
                    startLocation = "52.408266, 16.933519",
                    endLocation = "52.408266, 16.933519",
                    rating = 4.9f,
                    suggestedTime = "2 godziny",
                    curiosities = "Ratusz jest znany z koziołków trykających się codziennie o 12:00."
                ),
                Travel(
                    id = "2",
                    title = "Wycieczka do Katedry Poznańskiej",
                    description = "Zobacz Katedrę św. Piotra i Pawła oraz Ostrów Tumski.",
                    distanceKm = 3,
                    startLocation = "52.411022, 16.948634",
                    endLocation = "52.411022, 16.948634",
                    rating = 4.8f,
                    suggestedTime = "1.5 godziny",
                    curiosities = "Katedra jest najstarszą w Polsce, zbudowaną w X wieku."
                ),
                Travel(
                    id = "3",
                    title = "Wycieczka do Parku Cytadela",
                    description = "Spacer po największym parku w Poznaniu, z muzeami i pomnikami.",
                    distanceKm = 5,
                    startLocation = "52.421150, 16.939800",
                    endLocation = "52.421150, 16.939800",
                    rating = 4.7f,
                    suggestedTime = "3 godziny",
                    curiosities = "W parku znajduje się Muzeum Uzbrojenia oraz Muzeum Armii Poznań."
                ),
                Travel(
                    id = "4",
                    title = "Wycieczka do Jeziora Maltańskiego",
                    description = "Odwiedź jezioro, tor regatowy i Termy Maltańskie.",
                    distanceKm = 6,
                    startLocation = "52.401192, 16.973056",
                    endLocation = "52.401192, 16.973056",
                    rating = 4.6f,
                    suggestedTime = "4 godziny",
                    curiosities = "Jezioro Maltańskie jest sztucznym zbiornikiem wodnym, popularnym miejscem rekreacji."
                ),
                Travel(
                    id = "5",
                    title = "Wycieczka do Palmiarni Poznańskiej",
                    description = "Zobacz egzotyczne rośliny i akwaria w Palmiarni.",
                    distanceKm = 4,
                    startLocation = "52.396370, 16.900360",
                    endLocation = "52.396370, 16.900360",
                    rating = 4.8f,
                    suggestedTime = "2 godziny",
                    curiosities = "Palmiarnia Poznańska jest jedną z największych w Europie."
                ),
                Travel(
                    id = "6",
                    title = "Wycieczka do Zamku Cesarskiego",
                    description = "Odwiedź Zamek Cesarski i jego ogrody.",
                    distanceKm = 2,
                    startLocation = "52.406374, 16.921339",
                    endLocation = "52.406374, 16.921339",
                    rating = 4.7f,
                    suggestedTime = "1.5 godziny",
                    curiosities = "Zamek został zbudowany dla cesarza Wilhelma II w 1910 roku."
                ),
                Travel(
                    id = "7",
                    title = "Wycieczka do Muzeum Narodowego",
                    description = "Zobacz kolekcje sztuki polskiej i europejskiej.",
                    distanceKm = 2,
                    startLocation = "52.409538, 16.929930",
                    endLocation = "52.409538, 16.929930",
                    rating = 4.9f,
                    suggestedTime = "2 godziny",
                    curiosities = "Muzeum posiada bogatą kolekcję dzieł sztuki, w tym obrazy Jana Matejki."
                )
            )
        )
    }
}

@Composable
fun TravelListScreen(viewModel: TravelViewModel, modifier: Modifier = Modifier) {
    val travelList: List<Travel> by viewModel.travelData.collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.menu_title),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        travelList.forEach { trip ->
            TravelItem(trip = trip)
        }
    }
}

@Composable
fun TravelItem(trip: Travel) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "${trip.title}", style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onPrimary))
            Text(text = "${trip.description}", style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimary))
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val intent = Intent(context, TravelDetails::class.java).apply {
                        putExtra("travelId", trip.id)
                    }
                    context.startActivity(intent)
                },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text(text = "Szczegóły", color = MaterialTheme.colorScheme.onSecondary)
            }
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
