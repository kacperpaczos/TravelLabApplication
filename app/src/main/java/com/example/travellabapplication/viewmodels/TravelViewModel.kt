package com.example.travellabapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travellabapplication.models.Travel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TravelViewModel : ViewModel() {
    private val _travelData = MutableLiveData<List<Travel>>(emptyList())
    val travelData: LiveData<List<Travel>> = _travelData

    var travelDetails: MutableLiveData<Travel?> = MutableLiveData()

    fun loadTravelDetails(travelId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val travelDetail = _travelData.value?.find { it.id == travelId }
            travelDetails.postValue(travelDetail)
        }
    }

    fun setTravelData(travels: List<Travel>) {
        viewModelScope.launch(Dispatchers.IO) {
            _travelData.postValue(travels)
        }
    }

    fun getTravelById(travelId: String): MutableLiveData<Travel?> {
        val travelDetail = _travelData.value?.find { it.id == travelId }
        val result = MutableLiveData<Travel?>()
        result.postValue(travelDetail)
        return result
    }
}
