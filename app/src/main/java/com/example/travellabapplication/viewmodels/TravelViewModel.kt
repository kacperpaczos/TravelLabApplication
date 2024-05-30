package com.example.travellabapplication.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Travel(
    val id: String,
    val title: String,
    val description: String,
    val distanceKm: Int,
    val startLocation: String,
    val endLocation: String,
    val rating: Float,
    val suggestedTime: String,
    val curiosities: String
)

class TravelViewModel : ViewModel() {
    private val _travelData = MutableStateFlow<List<Travel>>(emptyList())
    val travelData: StateFlow<List<Travel>> = _travelData

    var travelDetails: MutableLiveData<Travel?> = MutableLiveData()

    fun loadTravelDetails(travelId: String) {
        val travelDetail = _travelData.value.find { it.id == travelId }
        travelDetails.postValue(travelDetail)
    }

    fun setTravelData(travels: List<Travel>) {
        _travelData.value = travels
    }
}
