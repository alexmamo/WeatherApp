package ro.alexmamo.weatherapp.city;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ro.alexmamo.weatherapp.cities.models.City;
import ro.alexmamo.weatherapp.city.models.CurrentWeather;
import ro.alexmamo.weatherapp.data.OpenWeatherMapApi;

import static ro.alexmamo.weatherapp.utils.Constants.APY_KEY;
import static ro.alexmamo.weatherapp.utils.Constants.TAG;

public class CurrentWeatherRepository {
    private OpenWeatherMapApi openWeatherMapApi;

    public CurrentWeatherRepository(OpenWeatherMapApi openWeatherMapApi) {
        this.openWeatherMapApi = openWeatherMapApi;
    }

    LiveData<CurrentWeather> addCurrentWeatherToLiveData(City city) {
        MutableLiveData<CurrentWeather> currentWeatherMutableLiveData = new MutableLiveData<>();
        Callback<CurrentWeather> callback = new Callback<CurrentWeather>() {
            @Override
            public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                if (currentWeather != null) {
                    currentWeatherMutableLiveData.setValue(currentWeather);
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                Log.d(TAG, t.getMessage());
            }
        };
        Call<CurrentWeather> call = openWeatherMapApi.getCurrentWeather(city.cityName, APY_KEY);
        call.enqueue(callback);
        return currentWeatherMutableLiveData;
    }

    String[] getDateAndTime() {
        Date date = Calendar.getInstance().getTime();
        String dayOfTheWeek = new SimpleDateFormat("E", Locale.US).format(date);
        String currentDate = new SimpleDateFormat("MMM dd, yyyy", Locale.US).format(date);
        String dayOfTheWeekAndCurrentDate = dayOfTheWeek + " " + currentDate;

        DateFormat currentTime = new SimpleDateFormat("HH:mm", Locale.US);
        String time = currentTime.format(date);

        return new String[]{dayOfTheWeekAndCurrentDate, time};
    }

    String getTemperature(CurrentWeather currentWeather) {
        return getCelsiusTemperature(currentWeather.main.temp) + "°C";
    }

    String getMin(CurrentWeather currentWeather) {
        return "Min: " + getCelsiusTemperature(currentWeather.main.temp_min) + "°C";
    }

    String getMax(CurrentWeather currentWeather) {
        return  "Max: " + getCelsiusTemperature(currentWeather.main.temp_max) + "°C";
    }

    String getWeather(CurrentWeather currentWeather) {
        return currentWeather.weather[0].main + ", " + currentWeather.weather[0].description;
    }

    String getWind(CurrentWeather currentWeather) {
        return "Wind: " + currentWeather.wind.speed + " Km/h";
    }

    String getPressure(CurrentWeather currentWeather) {
        return "Pressure: " + currentWeather.main.pressure;
    }

    String getHumidity(CurrentWeather currentWeather) {
        return "Humidity: " + currentWeather.main.humidity + "%";
    }

    private long getCelsiusTemperature(Double temp) {
        return Math.round(temp - 273.0);
    }
}