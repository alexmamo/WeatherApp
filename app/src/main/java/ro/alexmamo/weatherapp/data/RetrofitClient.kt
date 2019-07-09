package ro.alexmamo.weatherapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import ro.alexmamo.weatherapp.utils.Constants

class RetrofitClient private constructor() {
    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val openWeatherMapApi: OpenWeatherMapApi
        get() = retrofit.create(OpenWeatherMapApi::class.java)

    companion object {
        val instance: RetrofitClient
            @Synchronized get() {
                return RetrofitClient()
            }
    }
}