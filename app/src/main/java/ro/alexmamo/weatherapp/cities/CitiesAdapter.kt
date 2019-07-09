package ro.alexmamo.weatherapp.cities

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_city.view.*
import ro.alexmamo.weatherapp.R
import ro.alexmamo.weatherapp.cities.models.City

class CitiesAdapter internal constructor(
        private val context: Context,
        private val cityList: List<City>,
        private val onCityClickListener: OnCityClickListener
    ) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view, onCityClickListener)
    }

    override fun onBindViewHolder(cityViewHolder: CityViewHolder, position: Int) {
        val city = cityList[position]
        cityViewHolder.bindCity(city)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    inner class CityViewHolder(
            itemView: View,
            private var onCityClickListener: OnCityClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bindCity(city: City) {
            Glide.with(context).load(city.imageUrl).into(itemView.cityImageView)
            itemView.cityNameTextView.text = city.cityName
        }

        override fun onClick(v: View) {
            val position = adapterPosition
            val clickedCity = cityList[position]
            onCityClickListener.onCityClick(clickedCity)
        }
    }

    interface OnCityClickListener {
        fun onCityClick(clickedCity: City)
    }
}