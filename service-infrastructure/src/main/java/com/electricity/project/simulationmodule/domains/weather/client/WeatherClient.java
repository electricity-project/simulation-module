package com.electricity.project.simulationmodule.domains.weather.client;

import com.electricity.project.simulationmodule.domains.weather.data.Coordinates;
import com.electricity.project.simulationmodule.domains.weather.data.WeatherResponseAbstract;
import org.springframework.lang.NonNull;

public interface WeatherClient {

    WeatherResponseAbstract getRealTimeWeather(@NonNull Coordinates coordinates);
}
