package com.example.labtravelapp.ui.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labtravelapp.AppDatabase
import com.example.labtravelapp.Trip
import com.example.labtravelapp.TripStatus
import kotlinx.coroutines.launch

class TripViewModel(private val database: AppDatabase) : ViewModel() {
    private val _trips = MutableLiveData<List<Trip>>()
    val trips: LiveData<List<Trip>> = _trips
    private var currentStatus: TripStatus? = null

    fun setTripStatus(status: TripStatus) {
        currentStatus = status
        loadTrips()
    }

    fun getCurrentStatus(): TripStatus? = currentStatus

    private fun loadTrips() {
        viewModelScope.launch {
            currentStatus?.let { status ->
                _trips.value = database.tripDao().getTripsByStatus(status)
            }
        }
    }
}
