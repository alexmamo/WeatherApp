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
    @Override
    public void getCurrentWeather(City city, CurrentWeatherCallback currentWeatherCallback) {
        OpenWeatherMapApi openWeatherMapApi = RetrofitClient.getInstance().getOpenWeatherMapApi();
        Callback<CurrentWeather> callback = new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                if (currentWeather != null) {
                    currentWeatherCallback.onCurrentWeatherCallback(currentWeather);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        };
        Call<CurrentWeather> call = openWeatherMapApi.getCurrentWeather(city.cityName, APY_KEY);
        call.enqueue(callback);
    }

    public interface CurrentWeatherCallback {
        void onCurrentWeatherCallback(CurrentWeather currentWeather);
    }
}