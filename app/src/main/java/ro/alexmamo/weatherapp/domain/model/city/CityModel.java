package ro.alexmamo.weatherapp.domain.model.city;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.alexmamo.weatherapp.data.OpenWeatherMapApi;
import ro.alexmamo.weatherapp.data.RetrofitClient;
import ro.alexmamo.weatherapp.presentation.cities.model.CurrentWeather;
import ro.alexmamo.weatherapp.presentation.city.CityContract;
import ro.alexmamo.weatherapp.presentation.city.model.City;

import static ro.alexmamo.weatherapp.utils.Constants.APY_KEY;

public class CityModel implements CityContract.Model {
    private City city;

    public CityModel(City city) {
        this.city = city;
    }

    public void getCurrentWeather(CurrentWeatherCallback callback) {
        OpenWeatherMapApi openWeatherMapApi = RetrofitClient.getInstance().getOpenWeatherMapApi();
        Callback<CurrentWeather> currentWeatherCallback = new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                if (response.isSuccessful()) {
                    CurrentWeather currentWeather = response.body();
                    callback.onCurrentWeatherCallback(currentWeather);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        };
        Call<CurrentWeather> call = openWeatherMapApi.getCurrentWeather(city.cityName, APY_KEY);
        call.enqueue(currentWeatherCallback);
    }

    public interface CurrentWeatherCallback {
        void onCurrentWeatherCallback(CurrentWeather currentWeather);
        void onFailure(String errorMessage);
    }
}