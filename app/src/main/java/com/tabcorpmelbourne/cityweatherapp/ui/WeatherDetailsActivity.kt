package com.tabcorpmelbourne.cityweatherapp.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tabcorpmelbourne.cityweatherapp.R
import com.tabcorpmelbourne.cityweatherapp.model.description.WeatherDetails
import com.tabcorpmelbourne.cityweatherapp.network.WeatherService
import kotlinx.android.synthetic.main.activity_weather_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_details)
        val bundle: Bundle = intent.extras!!
        val id = bundle.get("id_value")
        getCurrentData(id as String)
    }

    internal fun getCurrentData(id: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WeatherService::class.java)
        val call = service.getCityWeatherDetails(id, AppId)
        call.enqueue(object : Callback<WeatherDetails> {
            override fun onResponse(
                call: Call<WeatherDetails>,
                response: Response<WeatherDetails>) {
                if (response.code() == 200) {
                    val weatherResponse = response.body()!!
                    showDetails(weatherResponse)
                }
            }

            override fun onFailure(call: Call<WeatherDetails>, t: Throwable) {
            }
        })
    }

    private fun showDetails(weatherResponse: WeatherDetails) {
        temp.text = "Temperature:  " + weatherResponse.main.temp
        temp_min.text = "Minimum Temperature:  " + weatherResponse.main.temp_min
        temp_max.text = "Maximum Temperature:  " + weatherResponse.main.temp_max
        weather_type.text = "Weather Type:  " + weatherResponse.weather[0].main
        description.text = "Weather Description:  " + weatherResponse.weather[0].description
        pressure.text = "Pressure:  " + weatherResponse.main.pressure
        humidity.text = "Humidity:  " + weatherResponse.main.humidity + "%"
        visibility.text = "Visibility:  " + weatherResponse.visibility + " mtrs"
        wind_speed.text = "Wind Speed:  " + weatherResponse.wind.speed + " kmph"
    }

    companion object {
        var BaseUrl = "https://samples.openweathermap.org"
        var AppId = "b1b15e88fa797225412429c1c50c122a1"
    }

}