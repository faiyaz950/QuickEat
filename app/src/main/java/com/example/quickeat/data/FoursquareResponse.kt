package com.example.quickeat.data


data class FoursquareResponse(
    val results: List<Place>
)

data class Place(
    val fsq_id: String,
    val name: String,
    val categories: List<Category>,
    val location: Location,
    val distance: Int
)

data class Category(
    val id: Int,
    val name: String,
    val short_name: String,
    val plural_name: String,
    val icon: Icon
)

data class Icon(
    val prefix: String,
    val suffix: String
)

data class Location(
    val address: String,
    val country: String,
    val locality: String?,
    val postcode: String,
    val formatted_address: String
)
