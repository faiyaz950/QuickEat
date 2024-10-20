package com.example.quickeat.data


import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FoursquareApi {
    @GET("/v3/places/search")
    suspend fun searchPlaces(
        @Query("query") query: String,
        @Query("ll") latLong: String,
        @Header("Authorization") token: String = "fsq3+3uS4RNYAtXgECTv+mwzdoKQzkZkkm9VuEhBcO1TNcM="
    ): FoursquareResponse
}
