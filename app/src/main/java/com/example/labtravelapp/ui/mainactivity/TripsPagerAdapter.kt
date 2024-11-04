package com.example.labtravelapp.ui.mainactivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.labtravelapp.TripStatus

class TripsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TripFragment.newInstance(TripStatus.COMPLETED)
            1 -> TripFragment.newInstance(TripStatus.IN_PROGRESS)
            2 -> TripFragment.newInstance(TripStatus.PLANNED)
            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }
} 