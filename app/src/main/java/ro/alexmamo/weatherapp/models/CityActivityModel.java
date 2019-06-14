package ro.alexmamo.weatherapp.models;

import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.alexmamo.weatherapp.OpenWeatherMapApi;
import ro.alexmamo.weatherapp.RetrofitClient;
import ro.alexmamo.weatherapp.contracts.CityActivityContract;
import ro.alexmamo.weatherapp.pojos.City;
import ro.alexmamo.weatherapp.pojos.CurrentWeather;

import static ro.alexmamo.weatherapp.Constants.APY_KEY;
import static ro.alexmamo.weatherapp.Constants.TAG;

public class CityActivityModel implements CityActivityContract.Model {
    public void getResponse(City city, ResponseCallback callback) {
        OpenWeatherMapApi openWeatherMapApi = RetrofitClient.getInstance().getOpenWeatherMapApi();
        Callback<CurrentWeather> currentWeatherCallback = new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                callback.onCallback(response);
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        };
        Call<CurrentWeather> call = openWeatherMapApi.getCurrentWeather(city.cityName, APY_KEY);
        call.enqueue(currentWeatherCallback);
    }

    public interface ResponseCallback {
        void onCallback(Response<CurrentWeather> response);
    }
}