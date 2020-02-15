package com.example.marsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherDay {

    @SerializedName("AT")
    private double temperature;

    @SerializedName("First_UTC")
    private String firstUTC;

    @SerializedName("HWS")
    private double windSpeed;

    @SerializedName("Last_UTC")
    private String lastUTC;

    @SerializedName("PRE")
    private double pressure;

    @SerializedName("Season")
    private String season;

    @SerializedName("WD")
    private String wind;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getFirstUTC() {
        return firstUTC;
    }

    public void setFirstUTC(String firstUTC) {
        this.firstUTC = firstUTC;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getLastUTC() {
        return lastUTC;
    }

    public void setLastUTC(String lastUTC) {
        this.lastUTC = lastUTC;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

//
//    @SerializedName("backdrop_path")
//    private String backdropImage;
//
//    @SerializedName("adult")
//    private Boolean adult;
//

/*    public CurrentSol(int sol_key, int sol_key1 ) {
        if (sol_key++ != sol_key1) {
            this.sol_key = sol_key1;
        }
        return sol_key;
    }*/

/*    public WeatherDay(double temperature, String firstUTC, double windSpeed, String lastUTC, double pressure, String season, String wind) {
        this.temperature = temperature;
        this.firstUTC = firstUTC;
        this.windSpeed = windSpeed;
        this.lastUTC = lastUTC;
        this.pressure = pressure;
        this.season = season;
        this.wind = wind;
    }*/

    public WeatherDay(double temperature, String firstUTC, double windSpeed, String lastUTC, double pressure, String season, String wind) {
        this.temperature = temperature;
        this.firstUTC = firstUTC;
        this.windSpeed = windSpeed;
        this.lastUTC = lastUTC;
        this.pressure = pressure;
        this.season = season;
        this.wind = wind;
    }

//        this.posterImage = posterImage;
//        this.backdropImage = backdropImage;
//        this.adult = adult;
//        this.genre_ids = genre_ids;
//        this.id = id;
//        this.original_title = original_title;
//        this.original_language = original_language;
//        this.popularity = popularity;
//        this.video = video;
//        this.vote_count = vote_count;
}

/*    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getAverageTemp() {
        return averageTemp;
    }

    public void setAverageTemp(double averageTemp) {
        this.averageTemp = averageTemp;
    }*/

//    public String getPosterImage() {
//        return posterImage;
//    }
//
//    public void setPosterImage(String posterImage) {
//        this.posterImage = posterImage;
//    }
//
//    public String getBackdropImage() {
//        return backdropImage;
//    }
//
//    public void setBackdropImage(String backdropImage) {
//        this.backdropImage = backdropImage;
//    }
//
//    public Boolean getAdult() {
//        return adult;
//    }
//
//    public void setAdult(Boolean adult) {
//        this.adult = adult;
//    }
//
//    public List<Integer> getGenre_ids() {
//        return genre_ids;
//    }
//
//    public void setGenre_ids(List<Integer> genre_ids) {
//        this.genre_ids = genre_ids;
//    }

