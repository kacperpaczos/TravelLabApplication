package com.example.travellabapplication.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Button
import android.os.SystemClock
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels

import com.example.travellabapplication.R
import com.example.travellabapplication.viewmodels.Travel
import com.example.travellabapplication.viewmodels.TravelViewModel

class TravelDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = TravelDetailsFragment()
    }

    private val viewModel: TravelViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var chronometer: Chronometer
    private var running = false
    private var pauseOffset: Long = 0
    private var paused = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_details_stoper, container, false)
        chronometer = view.findViewById(R.id.chronometer)

        val travelId: String? = arguments?.getString("travelId")
        travelId?.let {
            viewModel.loadTravelDetails(it)
        }

        viewModel.travelDetails.observe(viewLifecycleOwner) { travel: Travel? ->
            travel?.let {
                view.findViewById<TextView>(R.id.titleTextView).text = it.title
                view.findViewById<TextView>(R.id.descriptionTextView).text = it.description
            }
        }

        return view
    }

    private fun startChronometer() {
        if (!running) {
            chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
            chronometer.start()
            running = true
            paused = false
        }
        updateButtons()
    }

    private fun pauseChronometer() {
        if (running) {
            chronometer.stop()
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
            running = false
            paused = true
        }
        updateButtons()
    }

    private fun stopChronometer() {
        if (running || paused) {
            chronometer.stop()
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
            running = false
            paused = false
        }
        updateButtons()
    }

    private fun resetChronometer() {
        chronometer.stop()
        pauseOffset = 0
        chronometer.base = SystemClock.elapsedRealtime()
        running = false
        paused = false
        updateButtons()
    }

    private fun updateButtons() {
        view?.findViewById<Button>(R.id.startButton)?.visibility = if (!running && !paused) View.VISIBLE else View.GONE
        view?.findViewById<Button>(R.id.stopButton)?.visibility = if (running || paused) View.VISIBLE else View.GONE
        view?.findViewById<Button>(R.id.pauseButton)?.visibility = if (running) View.VISIBLE else View.GONE
        view?.findViewById<Button>(R.id.resetButton)?.visibility = if (!running && !paused) View.VISIBLE else View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.startButton).apply {
            setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_green_light))
            setOnClickListener {
                startChronometer()
            }
        }
        view.findViewById<Button>(R.id.stopButton).apply {
            setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_red_light))
            setOnClickListener {
                stopChronometer()
            }
        }
        view.findViewById<Button>(R.id.pauseButton).setOnClickListener {
            pauseChronometer()
        }
        view.findViewById<Button>(R.id.resetButton).setOnClickListener {
            resetChronometer()
        }
        updateButtons()
    }
}
