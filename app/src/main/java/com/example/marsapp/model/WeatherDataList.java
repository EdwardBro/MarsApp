package com.example.marsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherDataList {

    @SerializedName("sol_key")
    private List<WeatherDay> sol_key;

    public WeatherDataList(List<WeatherDay> sol_key){
        this.setSolKey(sol_key);
    }

    public List<WeatherDay> getSolKey() {
        return sol_key;
    }

    public void setSolKey(List<WeatherDay> sol_key) {
        this.sol_key = sol_key;
    }
}
