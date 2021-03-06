package ro.alexmamo.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ro.alexmamo.weatherapp.models.CurrentWeather;

public interface OpenWeatherMapApi {
    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String cityName, @Query("appid") String apiKey);
}