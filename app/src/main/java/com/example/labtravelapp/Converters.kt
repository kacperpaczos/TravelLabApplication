package com.example.labtravelapp

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromTripStatus(status: TripStatus): String {
        return status.name
    }

    @TypeConverter
    fun toTripStatus(status: String): TripStatus {
        return TripStatus.valueOf(status)
    }
} 