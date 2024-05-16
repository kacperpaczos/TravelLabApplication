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

import com.example.travellabapplication.R

class TravelDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = TravelDetailsFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    private lateinit var chronometer: Chronometer
    private var running = false
    private var pauseOffset: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        chronometer = view.findViewById(R.id.chronometer)
        return view
    }

    private fun startChronometer() {
        if (!running) {
            chronometer.base = SystemClock.elapsedRealtime() - pauseOffset
            chronometer.start()
            running = true
        } else {
            chronometer.start()
            running = true
        }
    }

    private fun pauseChronometer() {
        if (running) {
            chronometer.stop()
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
            running = false
        }
    }

    private fun stopChronometer() {
        if (running) {
            chronometer.stop()
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.base
            running = false
        }
    }

    private fun resetChronometer() {
        chronometer.stop()
        pauseOffset = 0
        chronometer.base = SystemClock.elapsedRealtime()
        running = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.startButton).setOnClickListener {
            startChronometer()
        }
        view.findViewById<Button>(R.id.stopButton).setOnClickListener {
            stopChronometer()
        }
        view.findViewById<Button>(R.id.pauseButton).setOnClickListener {
            pauseChronometer()
        }
        view.findViewById<Button>(R.id.resetButton).setOnClickListener {
            resetChronometer()
        }
    }
}