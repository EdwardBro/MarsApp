package com.example.marsapp.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.marsapp.database.WeatherRepository;
import com.example.marsapp.model.WeatherDataList;
import com.example.marsapp.model.WeatherDay;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivityViewModel extends AndroidViewModel {

    private WeatherRepository weatherRepository = new WeatherRepository();

    private MutableLiveData<List<WeatherDay>> weather = new MutableLiveData<>();
    private MutableLiveData<String> error = new MutableLiveData<>();

    public WeatherActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    public MutableLiveData<List<WeatherDay>> getWeather() {
        return weather;
    }

    public void getWeatherDay() {
        weatherRepository
                .getCurrentSol()
                .enqueue(new Callback<WeatherDataList>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherDataList> call, @NonNull Response<WeatherDataList> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            weather.setValue(response.body().getSolKey());
                        } else {
                            error.setValue("Api Error: " + response.message());
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<WeatherDataList> call, @NonNull Throwable t) {
                        error.setValue("Api Error: " + t.getMessage());
                    }
                });
    }

/*    public void getMovieYear(String year) {
        weatherRepository
                .getMovieYear(year)
                .enqueue(new Callback<WeatherDataList>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherDataList> call, @NonNull Response<WeatherDataList> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            weather.setValue(response.body().getResults());
                        } else {
                            error.setValue("Api Error: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<WeatherDataList> call, @NonNull Throwable t) {
                        error.setValue("Api Error: " + t.getMessage());
                    }
                });
    }*/
}
