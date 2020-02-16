package com.example.marsapp.database;

import com.example.marsapp.model.WeatherDataList;
import com.example.marsapp.model.WeatherDay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {

   @GET("/rss/api/?feed=weather&category=insight&feedtype=json&ver=1.0")
    Call<WeatherDataList> getWeatherData();

    @GET("/rss/api/?feed=weather&category=insight&feedtype=json&ver=1.0")
    Call<WeatherDataList> getSolKey(@Query("sol_key") String sol_key);

}