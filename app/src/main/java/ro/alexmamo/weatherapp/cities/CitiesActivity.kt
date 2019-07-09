package ro.alexmamo.weatherapp.cities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cities.*
import ro.alexmamo.weatherapp.R
import ro.alexmamo.weatherapp.cities.models.City
import ro.alexmamo.weatherapp.city.CityActivity
import java.util.*

class CitiesActivity : AppCompatActivity(), CitiesAdapter.OnCityClickListener {
    private val cityList = ArrayList<City>()
    private var citiesAdapter: CitiesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities)
        initCityAdapter()
        addCitiesToList()
    }

    private fun initCityAdapter() {
        citiesAdapter = CitiesAdapter(this, cityList, this)
        citiesRecyclerView!!.adapter = citiesAdapter
    }

    private fun addCitiesToList() {
        val citiesViewModel = ViewModelProviders.of(this).get(CitiesViewModel::class.java)
        val cityLiveData = citiesViewModel.citiesLiveData
        cityLiveData.observe(this, Observer { cities ->
            cityList.addAll(cities!!)
            citiesAdapter!!.notifyDataSetChanged()
        })
    }

    override fun onCityClick(clickedCity: City) {
        goToCityActivity(clickedCity)
    }

    private fun goToCityActivity(city: City) {
        val cityActivityIntent = Intent(this, CityActivity::class.java)
        cityActivityIntent.putExtra("city", city)
        startActivity(cityActivityIntent)
    }
}