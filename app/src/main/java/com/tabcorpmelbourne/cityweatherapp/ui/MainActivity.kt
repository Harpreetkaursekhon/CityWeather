package com.tabcorpmelbourne.cityweatherapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tabcorpmelbourne.cityweatherapp.R
import com.tabcorpmelbourne.cityweatherapp.`interface`.CellClickListener
import com.tabcorpmelbourne.cityweatherapp.adapter.CitiesAdapter
import com.tabcorpmelbourne.cityweatherapp.model.CitiesList
import com.tabcorpmelbourne.cityweatherapp.model.CitiesResponse
import com.tabcorpmelbourne.cityweatherapp.network.WeatherService
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CellClickListener {

    private var citiesList: List<CitiesList>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWeatherData()
    }

    internal fun getWeatherData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCurrentWeatherData(bbox, AppId)
        call.enqueue(object : Callback<CitiesResponse> {
            override fun onResponse(
                call: Call<CitiesResponse>,
                response: Response<CitiesResponse>
            ) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!
                    citiesList = weatherResponse.list
                    showCountries()
                }
            }

            override fun onFailure(call: Call<CitiesResponse>, t: Throwable) {
            }
        })
    }

    private fun showCountries() {
        recyclerViewCityList.layoutManager = LinearLayoutManager(applicationContext)
        recyclerViewCityList.adapter = CitiesAdapter(citiesList!!, this)
    }

    companion object {

        val BaseUrl = "https://samples.openweathermap.org"
        val AppId = "b1b15e88fa797225412429c1c50c122a1"
        var bbox = "12,32,15,37,10"

    }

    override fun onCellClickListener(data: String) {

        intent = Intent(this, WeatherDetailsActivity::class.java)
        intent.putExtra("id_value", data)
        startActivity(intent)
    }

}