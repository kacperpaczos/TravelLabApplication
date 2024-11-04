package com.example.labtravelapp.ui.detailtravelactivitydata
import com.example.labtravelapp.Trip
import androidx.lifecycle.*
import com.example.labtravelapp.AppDatabase
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Job

class DetailTravelActivityDataViewModel : ViewModel() {
    private lateinit var db: AppDatabase
    private val _trip = MutableLiveData<Trip>()
    val trip: LiveData<Trip> get() = _trip

    private val TAG = "DetailTravelDataVM"

    private var updateJob: Job? = null

    fun setDatabase(database: AppDatabase) {
        db = database
    }

    fun loadTrip(tripId: Int) {
        viewModelScope.launch {
            _trip.value = db.tripDao().getTripById(tripId)
        }
    }

    fun updateNotes(notes: String) {
        updateJob?.cancel()
        updateJob = viewModelScope.launch {
            try {
                delay(300)
                _trip.value?.let { currentTrip ->
                    Log.d(TAG, "Updating notes for trip ${currentTrip.id}: $notes")
                    val updatedTrip = currentTrip.copy(notes = notes)
                    db.tripDao().update(updatedTrip)
                    withContext(Dispatchers.Main) {
                        _trip.value = updatedTrip
                    }
                    Log.d(TAG, "Notes updated successfully")
                } ?: Log.e(TAG, "Cannot update notes - current trip is null")
            } catch (e: Exception) {
                Log.e(TAG, "Error updating notes", e)
            }
        }
    }
}
