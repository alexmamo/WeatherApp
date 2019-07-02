package ro.alexmamo.weatherapp.presentation.city;

import ro.alexmamo.weatherapp.domain.model.city.CityModel;

public interface CityContract {
    interface View {
        void initViews();
        void setDateAndTimeTextViews(String dayOfTheWeekAndCurrentDate, String time);
        void setTemperatureTextView(String temp);
        void setMinAndMaxTextViews(String min, String max);
        void setWeatherTextView(String weather);
        void setWindTextView(String wind);
        void setPressureTextView(String pressure);
        void setHumidityTextView(String humidity);
        void toastError(String errorMessage);
    }

    interface Presenter {
        void attachView(CityContract.View view);
        void detachView();
        boolean isViewAttached();
    }

    interface Model {
        void getCurrentWeather(CityModel.CurrentWeatherCallback callback);
    }
}