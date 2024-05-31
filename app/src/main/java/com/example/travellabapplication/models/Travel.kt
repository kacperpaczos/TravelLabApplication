package com.example.travellabapplication.models

data class Travel(
    val id: String,
    val title: String,
    val description: String,
    val distanceKm: Float,
    val startLocation: String,
    val endLocation: String,
    val rating: Float,
    val suggestedTime: String,
    val curiosities: String
)
