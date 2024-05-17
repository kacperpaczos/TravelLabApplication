package com.example.travellabapplication.viewmodels

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
    val rating: Float
)

class TravelViewModel : ViewModel() {
    private val _travelData = MutableStateFlow<List<Travel>>(emptyList())
    val travelData: StateFlow<List<Travel>> = _travelData

    fun setTravelData(
        id: String,
        title: String,
        description: String,
        distanceKm: Int,
        startLocation: String,
        endLocation: String,
        rating: Float
    ) {
        _travelData.value = listOf(Travel(id, title, description, distanceKm, startLocation, endLocation, rating))
    }
}