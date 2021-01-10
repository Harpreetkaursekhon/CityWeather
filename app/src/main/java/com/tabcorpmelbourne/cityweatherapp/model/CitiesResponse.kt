package com.tabcorpmelbourne.cityweatherapp.model


data class CitiesResponse(

    val cod: Int,
    val calctime: Double,
    val cnt: Int,
    val list: List<CitiesList>
)