package com.example.travellabapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travellabapplication.models.Travel
import com.example.travellabapplication.viewmodels.TravelViewModel

class TravelListFragment : Fragment() {

    private lateinit var viewModel: TravelViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TravelAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_travel_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TravelViewModel::class.java)
        recyclerView = view.findViewById(R.id.recyclerViewTravelList)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TravelAdapter { travel -> showTravelDetails(travel) }
        recyclerView.adapter = adapter

        viewModel.travelData.observe(viewLifecycleOwner, Observer { travelList: List<Travel> ->
            adapter.submitList(travelList)
        })

        // Przykładowe dane podróży po Poznaniu
        val poznanTravels = listOf(
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
        viewModel.setTravelData(poznanTravels)
    }

    private fun showTravelDetails(travel: Travel) {
        val intent = Intent(context, TravelDetailsActivity::class.java).apply {
            putExtra("travelId", travel.id)
        }
        startActivity(intent)
    }

    companion object {
        fun newInstance() = TravelListFragment()
    }
}