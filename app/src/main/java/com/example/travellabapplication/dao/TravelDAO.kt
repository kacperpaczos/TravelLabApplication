package com.example.travellabapplication.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.travellabapplication.models.Travel

@Dao
interface TravelDao {
    @Query("SELECT * FROM travels")
    fun getAllTravels(): LiveData<List<Travel>>

    @Query("SELECT * FROM travels WHERE id = :travelId")
    fun getTravelById(travelId: String): LiveData<Travel>

    @Insert
    fun insertAll(travels: List<Travel>)
}
