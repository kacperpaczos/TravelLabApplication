package com.example.labtravelapp.ui.mainactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.labtravelapp.AppDatabase
import com.example.labtravelapp.R
import com.example.labtravelapp.Trip
import kotlinx.coroutines.launch

class NoTripsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_no_trips, container, false)

        val db = Room.databaseBuilder(
            requireContext().applicationContext,
            AppDatabase::class.java, "trips-database"
        ).build()

        view.findViewById<Button>(R.id.fill_button).setOnClickListener {
            lifecycleScope.launch {
                val tripDao = db.tripDao()
                val trips = listOf(
                    Trip(name = "Spacer po Starym Rynku", description = "Wycieczka po Starym Rynku w Poznaniu. Zobaczymy Ratusz, słynne koziołki oraz piękne kamienice. Spacer zakończymy w jednej z lokalnych kawiarni.", date = "2023-10-01", startLocation = "Stary Rynek", endLocation = "Kawiarnia na Starym Rynku", estimatedDistance = 1.0, estimatedTime = 1.0, cost = 50.0, rating = 4.5f, guide = "Anna Kowalska"),
                    Trip(name = "Ostrów Tumski i Warta", description = "Odwiedzimy Ostrów Tumski, najstarszą część Poznania. Zobaczymy Katedrę oraz Muzeum Archidiecezjalne. Wycieczka zakończy się spacerem nad Wartą.", date = "2023-10-02", startLocation = "Ostrów Tumski", endLocation = "Nad Wartą", estimatedDistance = 2.5, estimatedTime = 2.0, cost = 70.0, rating = 4.0f, guide = "Jan Nowak"),
                    Trip(name = "Zamek Cesarski i Park Mickiewicza", description = "Zwiedzimy Zamek Cesarski oraz jego ogrody. Dowiemy się o historii zamku i jego roli w czasie II wojny światowej. Spacer zakończymy w pobliskim parku.", date = "2023-10-03", startLocation = "Zamek Cesarski", endLocation = "Park Mickiewicza", estimatedDistance = 1.0, estimatedTime = 1.0, cost = 60.0, rating = 4.8f, guide = "Katarzyna Wiśniewska"),
                    Trip(name = "Jeżyce i Park Sołacki", description = "Wycieczka po Jeżycach, jednej z najciekawszych dzielnic Poznania. Zobaczymy zabytkowe kamienice, odwiedzimy lokalne sklepy i kawiarnie. Spacer zakończymy w Parku Sołackim.", date = "2023-10-04", startLocation = "Jeżyce", endLocation = "Park Sołacki", estimatedDistance = 3.0, estimatedTime = 2.0, cost = 80.0, rating = 4.2f, guide = "Michał Lewandowski"),
                    Trip(name = "Nowe Zoo i Piknik", description = "Odwiedzimy Nowe Zoo w Poznaniu. Zobaczymy różnorodne gatunki zwierząt, a także weźmiemy udział w pokazie karmienia. Wycieczka zakończy się piknikiem na terenie zoo.", date = "2023-10-05", startLocation = "Nowe Zoo", endLocation = "Nowe Zoo", estimatedDistance = 4.0, estimatedTime = 3.0, cost = 100.0, rating = 4.7f, guide = "Ewa Zielińska")
                )
                trips.forEach { trip ->
                    tripDao.insert(trip)
                }
                // Po dodaniu wycieczek, przeładuj aplikację, aby pokazać listę wycieczek
                requireActivity().recreate()
            }
        }

        return view
    }
}