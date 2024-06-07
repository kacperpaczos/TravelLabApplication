package com.example.labtravelapp.ui.detailtravelactivitystoper

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.labtravelapp.R
import com.example.labtravelapp.ui.detailtravelactivitydata.DetailTravelActivityDataViewModel

class DetailTravelActivityStoperFragment : Fragment() {
    private lateinit var timerTextView: TextView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var resumeButton: Button
    private lateinit var finishButton: Button
    private lateinit var resetButton: Button
    private var handler = Handler(Looper.getMainLooper())
    private var seconds = 0
    private var running = false
    private var wasRunning = false

    private val viewModel: DetailTravelActivityDataViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail_travel_activity_stoper, container, false)
        timerTextView = rootView.findViewById(R.id.timer_text_view)
        startButton = rootView.findViewById(R.id.start_button)
        pauseButton = rootView.findViewById(R.id.pause_button)
        resumeButton = rootView.findViewById(R.id.resume_button)
        finishButton = rootView.findViewById(R.id.finish_button)
        resetButton = rootView.findViewById(R.id.reset_button)

        timerTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.timer_default))

        startButton.setOnClickListener {
            running = true
            wasRunning = true
            startButton.visibility = View.GONE
            pauseButton.visibility = View.VISIBLE
            updateTimerTextColor()
        }

        pauseButton.setOnClickListener {
            running = false
            pauseButton.visibility = View.GONE
            resumeButton.visibility = View.VISIBLE
            resetButton.visibility = View.VISIBLE
            updateTimerTextColor()
        }

        resumeButton.setOnClickListener {
            running = true
            pauseButton.visibility = View.VISIBLE
            resumeButton.visibility = View.GONE
            resetButton.visibility = View.GONE
            updateTimerTextColor()
        }

        resetButton.setOnClickListener {
            running = false
            wasRunning = false
            seconds = 0
            updateTimerText()
            startButton.visibility = View.VISIBLE
            pauseButton.visibility = View.GONE
            resumeButton.visibility = View.GONE
            finishButton.visibility = View.GONE
            resetButton.visibility = View.GONE
            timerTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.timer_default))
            timerTextView.text = "00:00:00" // Ustawienie wartości po resecie na 00:00:00
        }

        finishButton.setOnClickListener {
            running = false
        }

        runTimer()
        return rootView
    }

    private fun runTimer() {
        handler.post(object : Runnable {
            override fun run() {
                if (running) {
                    seconds++
                    updateTimerText()
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun updateTimerText() {
        val hours = seconds / 3600
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60
        val time = String.format("%d:%02d:%02d", hours, minutes, secs)
        timerTextView.text = time
        updateTimerTextColor()
    }

    private fun updateTimerTextColor() {
        val color = when {
            running -> R.color.timer_running
            wasRunning -> R.color.timer_paused
            else -> R.color.timer_default
        }
        timerTextView.setTextColor(ContextCompat.getColor(requireContext(), color))
    }
}
