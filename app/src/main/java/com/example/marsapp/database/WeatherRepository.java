package com.example.marsapp.database;

import com.example.marsapp.model.WeatherDataList;
import com.example.marsapp.model.WeatherDay;

import retrofit2.Call;

public class WeatherRepository {

    private WeatherApiService weatherApiService = WeatherApi.create();

    public Call<WeatherDataList> getCurrentSol() {
        return weatherApiService.getWeatherData();
    }

}
