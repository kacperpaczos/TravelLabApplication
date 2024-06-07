package com.example.labtravelapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Trip::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
}