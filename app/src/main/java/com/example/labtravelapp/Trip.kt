package com.example.labtravelapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
@Entity(tableName = "trips")
@TypeConverters(Converters::class)
data class Trip(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val notes: String = "",
    val date: String,
    val startLocation: String,
    val endLocation: String,
    val estimatedDistance: Double,
    val estimatedTime: Double,
    val cost: Double,
    val rating: Float,
    val guide: String,
    val status: TripStatus
) 
