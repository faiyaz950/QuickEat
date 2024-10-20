package com.example.quickeat.domain


import com.example.quickeat.data.FoursquareRepository
import com.example.quickeat.data.Place
import javax.inject.Inject

class SearchPlacesUseCase @Inject constructor(
    private val repository: FoursquareRepository
) {
    suspend operator fun invoke(query: String, latLong: String): List<Place> {
        return repository.searchPlaces(query, latLong)
    }
}
