package ro.alexmamo.weatherapp.presentation.cities.pojo;

public class CurrentWeather {
    public Weather[] weather;
    public Main main;
    public Wind wind;

    public class Weather {
        public String main, description;
    }

    public class Main {
        public double temp, temp_min, temp_max;
        public int pressure, humidity;
    }

    public class Wind {
        public String speed;
    }
}