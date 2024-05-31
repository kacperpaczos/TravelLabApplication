package com.example.travellabapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.travellabapplication.viewmodels.TravelViewModel

class TravelDetailsFragment : Fragment() {

    private lateinit var viewModel: TravelViewModel
    private var travelId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            travelId = it.getString(ARG_TRAVEL_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_travel_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TravelViewModel::class.java)

        travelId?.let {
            viewModel.getTravelById(it).observe(viewLifecycleOwner, { travel ->
                // Update UI with travel details
            })
        }
    }

    companion object {
        private const val ARG_TRAVEL_ID = "travelId"

        fun newInstance(travelId: String?) = TravelDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TRAVEL_ID, travelId)
            }
        }
    }
}
