package com.electricity.project.weather.client;

import com.electricity.project.weather.data.Coordinates;
import com.electricity.project.weather.data.WeatherResponseAbstract;
import org.springframework.lang.NonNull;

public interface WeatherClient {

    WeatherResponseAbstract getRealTimeWeather(@NonNull Coordinates coordinates);
}
