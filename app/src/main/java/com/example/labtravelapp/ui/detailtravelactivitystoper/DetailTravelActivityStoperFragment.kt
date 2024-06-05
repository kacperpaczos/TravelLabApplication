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
import com.example.labtravelapp.R

class DetailTravelActivityStoperFragment : Fragment() {
    private lateinit var timerTextView: TextView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var pauseButton: Button
    private lateinit var resumeButton: Button
    private lateinit var finishButton: Button
    private var handler = Handler(Looper.getMainLooper())
    private var seconds = 0
    private var running = false
    private var wasRunning = false

    companion object {
        fun newInstance(param1: String, param2: String) =
            DetailTravelActivityStoperFragment().apply {
                arguments = Bundle().apply {
                    putString("param1", param1)
                    putString("param2", param2)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_detail_travel_activity_stoper, container, false)
        timerTextView = rootView.findViewById(R.id.timer_text_view)
        startButton = rootView.findViewById(R.id.start_button)
        stopButton = rootView.findViewById(R.id.stop_button)
        pauseButton = rootView.findViewById(R.id.pause_button)
        resumeButton = rootView.findViewById(R.id.resume_button)
        finishButton = rootView.findViewById(R.id.finish_button)

        startButton.setOnClickListener {
            running = true
            startButton.visibility = View.GONE
            pauseButton.visibility = View.VISIBLE
            updateStopButtonColor()
        }

        stopButton.setOnClickListener {
            running = false
            seconds = 0
            updateTimerText()
            startButton.visibility = View.VISIBLE
            pauseButton.visibility = View.GONE
            resumeButton.visibility = View.GONE
            finishButton.visibility = View.GONE
            updateStopButtonColor()
        }

        pauseButton.setOnClickListener {
            running = false
            pauseButton.visibility = View.GONE
            resumeButton.visibility = View.VISIBLE
            updateStopButtonColor()
        }

        resumeButton.setOnClickListener {
            running = true
            pauseButton.visibility = View.VISIBLE
            resumeButton.visibility = View.GONE
            updateStopButtonColor()
        }

        finishButton.setOnClickListener {
            running = false
            // Add any additional logic for finishing the timer
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
    }

    private fun updateStopButtonColor() {
        if (running) {
            stopButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green)
        } else if (wasRunning) {
            stopButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.red)
        } else {
            stopButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.yellow)
        }
    }
}
