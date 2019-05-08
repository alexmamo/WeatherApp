package ro.alexmamo.weatherapp.adapters;

import android.content.Context;
import android.content.Intent;
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
import ro.alexmamo.weatherapp.activities.CityActivity;
import ro.alexmamo.weatherapp.models.City;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private Context context;
    private List<City> cityList;

    public CityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder cityViewHolder, int position) {
        City city = cityList.get(position);
        cityViewHolder.bind(city);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView cityImageView;
        TextView cityNameTextView;

        CityViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            cityImageView = itemView.findViewById(R.id.city_image_view);
            cityNameTextView = itemView.findViewById(R.id.city_name_text_view);
        }

        void bind(City city) {
            Glide.with(context).load(city.imageUrl).into(cityImageView);
            cityNameTextView.setText(city.cityName);
            itemView.setOnClickListener(view -> {
                Intent cityActivityIntent = new Intent(context, CityActivity.class);
                cityActivityIntent.putExtra("city", city);
                context.startActivity(cityActivityIntent);
            });
        }
    }
}