package com.example.labtravelapp.ui.mainactivity

import android.content.Intent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.launch
import com.example.labtravelapp.AppDatabase
import com.example.labtravelapp.DetailTravelActivity
import com.example.labtravelapp.R
import android.util.Log
import com.example.labtravelapp.TripStatus
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.example.labtravelapp.databinding.FragmentTripListBinding
import androidx.lifecycle.Observer
import com.example.labtravelapp.Trip
import androidx.lifecycle.ViewModelProvider
import com.example.labtravelapp.TravelApplication
import android.widget.Toast

class TripFragment : Fragment() {

    private val TAG = "TripFragment"
    private var _binding: FragmentTripListBinding? = null
    private val binding get() = _binding!!
    private lateinit var tripAdapter: MyTripRecyclerViewAdapter
    private var tripStatus: TripStatus? = null
    private lateinit var viewModel: TripViewModel

    companion object {
        private const val ARG_TRIP_STATUS = "trip_status"

        fun newInstance(status: TripStatus): TripFragment {
            return TripFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_TRIP_STATUS, status)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = (requireActivity().application as TravelApplication).database
        val factory = TripViewModelFactory(database)
        viewModel = ViewModelProvider(this, factory)[TripViewModel::class.java]
        
        arguments?.let {
            val status = it.getSerializable(ARG_TRIP_STATUS) as TripStatus
            Log.d(TAG, "Fragment created with status: $status")
            viewModel.setTripStatus(status)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTripListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeTrips()
    }

    private fun setupRecyclerView() {
        tripAdapter = MyTripRecyclerViewAdapter(emptyList()) { trip ->
            navigateToDetail(trip)
        }
        binding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = tripAdapter
        }
    }

    private fun observeTrips() {
        viewModel.trips.observe(viewLifecycleOwner) { trips ->
            Log.d(TAG, "Received trips: ${trips.size} for status: ${viewModel.getCurrentStatus()}")
            tripAdapter.updateTrips(trips)
            
            if (trips.isEmpty()) {
                binding.list.visibility = View.GONE
            } else {
                binding.list.visibility = View.VISIBLE
            }
        }
    }

    private fun navigateToDetail(trip: Trip) {
        try {
            Log.d(TAG, "Próba przejścia do szczegółów wycieczki: ${trip.id}")
            val intent = Intent(requireContext(), DetailTravelActivity::class.java).apply {
                putExtra("TRIP_ID", trip.id)
            }
            startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Błąd podczas przechodzenia do szczegółów", e)
            Toast.makeText(requireContext(), "Nie można otworzyć szczegółów wycieczki", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
