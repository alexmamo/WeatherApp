package ro.alexmamo.weatherapp.presentation.city;

import ro.alexmamo.weatherapp.domain.model.city.CityActivityModel;
import ro.alexmamo.weatherapp.presentation.city.model.City;

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
        void toastError(String errorMessage);
    }

    interface Presenter {
        void attachView(CityActivityContract.View view);
        void detachView();
        boolean isViewAttached();
        void setWeatherDataToViews();
    }

    interface Model {
        void getResponse(City city, CityActivityModel.ResponseCallback callback);
    }
}