package ro.alexmamo.weatherapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ro.alexmamo.weatherapp.R;
import ro.alexmamo.weatherapp.models.City;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private Context context;
    private List<City> cityList;
    private OnCityClickListener onCityClickListener;

    public CityAdapter(Context context, List<City> cityList, OnCityClickListener onCityClickListener) {
        this.context = context;
        this.cityList = cityList;
        this.onCityClickListener = onCityClickListener;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view, onCityClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int position) {
        City city = cityList.get(position);
        cityViewHolder.bindCity(city);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView cityImageView;
        TextView cityNameTextView;
        OnCityClickListener onCityClickListener;

        CityViewHolder(View itemView, OnCityClickListener onCityClickListener) {
            super(itemView);
            cityImageView = itemView.findViewById(R.id.city_image_view);
            cityNameTextView = itemView.findViewById(R.id.city_name_text_view);
            this.onCityClickListener = onCityClickListener;
            itemView.setOnClickListener(this);
        }

        void bindCity(City city) {
            Glide.with(context).load(city.imageUrl).into(cityImageView);
            cityNameTextView.setText(city.cityName);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            onCityClickListener.onCityClick(position);
        }
    }

    public interface OnCityClickListener {
        void onCityClick(int position);
    }
}