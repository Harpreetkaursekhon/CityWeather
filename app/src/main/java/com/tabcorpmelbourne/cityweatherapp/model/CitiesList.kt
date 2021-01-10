package com.tabcorpmelbourne.cityweatherapp.model


data class CitiesList(

    val id: Int,
    val name: String,
    val coord: Coord,
    val main: Main,
    val dt: Int,
    val wind: Wind,
    val rain: Rain,
    val clouds: Clouds,
    val weather: List<Weather>
)