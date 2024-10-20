package com.example.quickeat.data


import javax.inject.Inject

class FoursquareRepository @Inject constructor(
    private val api: FoursquareApi
) {
    suspend fun searchPlaces(query: String, latLong: String): List<Place> {
        return api.searchPlaces(query, latLong).results
    }
}
