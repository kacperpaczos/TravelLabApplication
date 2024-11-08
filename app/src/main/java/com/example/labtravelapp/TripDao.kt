package com.example.labtravelapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TripDao {
    @Insert
    suspend fun insert(trip: Trip)

    @Update
    suspend fun update(trip: Trip)

    @Query("SELECT * FROM trips")
    suspend fun getAllTrips(): List<Trip>

    @Query("SELECT * FROM trips WHERE id = :tripId")
    suspend fun getTripById(tripId: Int): Trip?

    @Query("SELECT * FROM trips WHERE status = :status")
    suspend fun getTripsByStatus(status: TripStatus): List<Trip>
} 