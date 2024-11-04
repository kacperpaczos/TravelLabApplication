package com.example.labtravelapp.ui.detailtravelactivitydata

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labtravelapp.R

class DetailTravelActivityDataFragment : Fragment() {

    private val TAG = "DetailTravelDataFragment"
    private lateinit var viewModel: DetailTravelActivityDataViewModel
    private lateinit var tripDataAdapter: TripDataAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "Tworzenie widoku fragmentu")
        return inflater.inflate(R.layout.fragment_detail_travel_activity_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Inicjalizacja fragmentu")

        try {
            recyclerView = view.findViewById(R.id.trip_details_recycler_view)
            recyclerView.layoutManager = LinearLayoutManager(context).apply {
                isSmoothScrollbarEnabled = true
            }
            
            // Włączamy zagnieżdżone scrollowanie
            recyclerView.isNestedScrollingEnabled = true
            
            viewModel = ViewModelProvider(requireActivity()).get(DetailTravelActivityDataViewModel::class.java)
            
            viewModel.trip.observe(viewLifecycleOwner) { trip ->
                trip?.let {
                    Log.d(TAG, "Zaktualizowano dane wycieczki: ${trip.name}")
                    if (!::tripDataAdapter.isInitialized) {
                        tripDataAdapter = TripDataAdapter(
                            requireContext(), 
                            trip
                        ) { notes ->
                            view.post {
                                viewModel.updateNotes(notes)
                            }
                        }
                        recyclerView.adapter = tripDataAdapter
                    } else {
                        tripDataAdapter.data = trip
                        view.post {
                            tripDataAdapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas inicjalizacji fragmentu", e)
        }
    }
}
