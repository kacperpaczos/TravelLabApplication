package com.example.labtravelapp.ui.detailtravelactivitydata

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.labtravelapp.R

class DetailTravelActivityDataFragment : Fragment() {

    companion object {
        fun newInstance() = DetailTravelActivityDataFragment()
    }

    private val viewModel: DetailTravelActivityDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_detail_travel_activity_data, container, false)
    }

}