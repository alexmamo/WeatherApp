package ro.alexmamo.weatherapp.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ro.alexmamo.weatherapp.city.CurrentWeatherViewModel;
import ro.alexmamo.weatherapp.city.CurrentWeatherViewModelFactory;

@Module
@SuppressWarnings("unused")
abstract class CurrentWeatherViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindCurrentWeatherViewModelFactory(CurrentWeatherViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(CurrentWeatherViewModel.class)
    abstract ViewModel provideCurrentWeatherViewModel(CurrentWeatherViewModel viewModel);
}