package com.example.labtravelapp.ui.detailtravelactivitydata

import androidx.lifecycle.*
import com.example.labtravelapp.AppDatabase
import com.example.labtravelapp.Trip
import kotlinx.coroutines.launch

class DetailTravelActivityDataViewModel : ViewModel() {
    private lateinit var db: AppDatabase
    private val _trip = MutableLiveData<Trip>()
    val trip: LiveData<Trip> get() = _trip
    private val _trips = MutableLiveData<List<Trip>>()
    val trips: LiveData<List<Trip>> get() = _trips

    fun setDatabase(database: AppDatabase) {
        db = database
    }

    fun loadTrip(tripId: Int) {
        viewModelScope.launch {
            _trip.value = db.tripDao().getTripById(tripId)
        }
    }

    fun loadTrips() {
        viewModelScope.launch {
            _trips.value = db.tripDao().getAllTrips()
        }
    }
}
