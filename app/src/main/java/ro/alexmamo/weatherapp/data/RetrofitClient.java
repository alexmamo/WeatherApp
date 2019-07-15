package ro.alexmamo.weatherapp.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ro.alexmamo.weatherapp.utils.Constants.BASE_URL;

public class RetrofitClient {
    private static RetrofitClient retrofitClient;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){
        if(retrofitClient == null){
            retrofitClient = new RetrofitClient();
        }
        return retrofitClient;
    }

    public OpenWeatherMapApi getOpenWeatherMapApi(){
        return retrofit.create(OpenWeatherMapApi.class);
    }
}