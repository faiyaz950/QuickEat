package com.example.quickeat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import android.location.Location
import com.example.quickeat.data.FoursquareApi
import com.example.quickeat.data.Place

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: FoursquareApi
) : ViewModel() {

    private val _places = MutableStateFlow<List<Place>>(emptyList())
    val places: StateFlow<List<Place>> = _places

    // Use a private variable to store userLocation
    private var _userLocation: String? = null

    // Provide a setter for userLocation
    var userLocation: String?
        get() = _userLocation
        private set(value) {
            _userLocation = value
        }

    fun setUserLocation(location: Location) {
        // Set user location as a formatted string
        userLocation = "${location.latitude},${location.longitude}"
    }

    fun searchPlaces(query: String) {
        viewModelScope.launch {
            userLocation?.let { latLong ->
                val response = api.searchPlaces(query, latLong)
                _places.value = response.results
            }
        }
    }
}
