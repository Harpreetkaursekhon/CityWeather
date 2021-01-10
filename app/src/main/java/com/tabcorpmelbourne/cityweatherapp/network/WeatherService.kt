package com.tabcorpmelbourne.cityweatherapp.network

import com.tabcorpmelbourne.cityweatherapp.model.CitiesResponse
import com.tabcorpmelbourne.cityweatherapp.model.description.WeatherDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/box/city")
    fun getCurrentWeatherData(@Query("bbox") bbox: String, @Query("appid") apiKey: String): Call<CitiesResponse>

    @GET("/data/2.5/weather")
    fun getCityWeatherDetails(@Query("id") id: String, @Query("appid") apiKey: String): Call<WeatherDetails>

}