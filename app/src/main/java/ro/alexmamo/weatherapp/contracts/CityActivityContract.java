package ro.alexmamo.weatherapp.contracts;

import ro.alexmamo.weatherapp.models.CityActivityModel;
import ro.alexmamo.weatherapp.pojos.City;

public interface CityActivityContract {
    interface View {
        void initViews();
        void setDateAndTimeTextViews(String dayOfTheWeekAndCurrentDate, String time);
        void setTemperatureTextView(String temp);
        void setMinAndMaxTextViews(String min, String max);
        void setWeatherTextView(String weather);
        void setWindTextView(String wind);
        void setPressureTextView(String pressure);
        void setHumidityTextView(String humidity);
    }

    interface Presenter {
        void setWeatherDataToViews();
    }

    interface Model {
        void getCurrentWeather(City city, CityActivityModel.CurrentWeatherCallback currentWeatherCallback);
    }
}