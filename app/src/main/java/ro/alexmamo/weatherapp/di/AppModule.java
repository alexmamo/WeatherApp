package ro.alexmamo.weatherapp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ro.alexmamo.weatherapp.city.CurrentWeatherRepository;
import ro.alexmamo.weatherapp.data.OpenWeatherMapApi;

import static ro.alexmamo.weatherapp.utils.Constants.BASE_URL;

@Module
class AppModule {
    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static OpenWeatherMapApi provideOpenWeatherMapApi(Retrofit retrofit){
        return retrofit.create(OpenWeatherMapApi.class);
    }

    @Provides
    static CurrentWeatherRepository provideCurrentWeatherRepository(OpenWeatherMapApi openWeatherMapApi) {
        return new CurrentWeatherRepository(openWeatherMapApi);
    }
}