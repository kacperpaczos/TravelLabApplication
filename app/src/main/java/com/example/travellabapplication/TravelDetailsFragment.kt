package com.example.travellabapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.travellabapplication.models.Travel
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
                travel?.let { updateUI(view, it) }
            })
        }

        val travelIdTextView: TextView = view.findViewById(R.id.textViewTravelId)
        travelIdTextView.text = travelId

        val buttonBack: Button = view.findViewById(R.id.buttonBack)
        buttonBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun updateUI(view: View, travel: Travel) {
        view.findViewById<TextView>(R.id.textViewTravelId).text = travel.id
        view.findViewById<TextView>(R.id.textViewTitle).text = travel.title
        view.findViewById<TextView>(R.id.textViewDescription).text = travel.description
        view.findViewById<TextView>(R.id.textViewDistance).text = travel.distanceKm.toString()
        view.findViewById<TextView>(R.id.textViewStartLocation).text = travel.startLocation
        view.findViewById<TextView>(R.id.textViewEndLocation).text = travel.endLocation
        view.findViewById<TextView>(R.id.textViewRating).text = travel.rating.toString()
        view.findViewById<TextView>(R.id.textViewSuggestedTime).text = travel.suggestedTime
        view.findViewById<TextView>(R.id.textViewCuriosities).text = travel.curiosities
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
