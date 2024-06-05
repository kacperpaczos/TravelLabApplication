package com.example.travellabapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travellabapplication.data.TravelDatabase
import com.example.travellabapplication.models.Travel
import kotlinx.coroutines.launch

class TravelViewModel(application: Application) : AndroidViewModel(application) {
    private val travelDao = TravelDatabase.getDatabase(application).travelDao()
    val travelData: LiveData<List<Travel>> = travelDao.getAllTravels()
    private val _travelDetails = MutableLiveData<Travel?>()
    val travelDetails: LiveData<Travel?> get() = _travelDetails

    fun loadTravelDetails(travelId: String): LiveData<Travel?> {
        // Załaduj szczegóły podróży i ustaw wartość _travelDetails
        // Przykład:
        // _travelDetails.value = ... // załaduj dane z repozytorium lub innego źródła
        return _travelDetails
    }

    fun insertTravels(vararg travels: Travel) {
        viewModelScope.launch {
            travelDao.insertAll(travels.toList())
        }
    }
}
