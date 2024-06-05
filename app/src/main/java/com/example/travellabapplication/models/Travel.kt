package com.example.travellabapplication.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "travels")
data class Travel(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val distanceKm: Float,
    val startLocation: String,
    val endLocation: String,
    val rating: Float,
    val suggestedTime: String,
    val curiosities: String
)
