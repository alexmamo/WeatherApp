package ro.alexmamo.weatherapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ro.alexmamo.weatherapp.city.CityActivity;

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract CityActivity contributeCityActivity();
}