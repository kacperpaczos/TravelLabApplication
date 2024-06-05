package com.example.labtravelapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Trip::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun tripDao(): TripDao
}