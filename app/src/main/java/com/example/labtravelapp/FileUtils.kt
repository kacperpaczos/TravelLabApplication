package com.example.labtravelapp.utils

import android.content.Context
import com.example.labtravelapp.Trip
import com.example.labtravelapp.TripStatus
import org.json.JSONObject
import java.io.IOException

object FileUtils {
    private const val TRIPS_FILE = "trips.json"

    fun loadTripsFromAssets(context: Context): List<Trip> {
        try {
            val jsonString = context.assets.open(TRIPS_FILE)
                .bufferedReader()
                .use { it.readText() }

            val jsonObject = JSONObject(jsonString)
            val tripsArray = jsonObject.getJSONArray("trips")
            val tripsList = mutableListOf<Trip>()

            for (i in 0 until tripsArray.length()) {
                val tripJson = tripsArray.getJSONObject(i)
                tripsList.add(
                    Trip(
                        name = tripJson.getString("name"),
                        description = tripJson.getString("description"),
                        notes = tripJson.optString("notes", ""),
                        date = tripJson.getString("date"),
                        startLocation = tripJson.getString("startLocation"),
                        endLocation = tripJson.getString("endLocation"),
                        estimatedDistance = tripJson.getDouble("estimatedDistance"),
                        estimatedTime = tripJson.getDouble("estimatedTime"),
                        cost = tripJson.getDouble("cost"),
                        rating = tripJson.getDouble("rating").toFloat(),
                        guide = tripJson.getString("guide"),
                        status = TripStatus.valueOf(tripJson.getString("status"))
                    )
                )
            }
            return tripsList
        } catch (e: IOException) {
            e.printStackTrace()
            return emptyList()
        }
    }
}
