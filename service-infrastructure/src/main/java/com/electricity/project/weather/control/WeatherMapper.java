package com.electricity.project.weather.control;

import com.electricity.project.weather.data.WeatherResponseAbstract;
import com.electricity.project.weather.entity.WeatherEntity;

public class WeatherMapper {

    public static WeatherEntity mapToEntity(WeatherResponseAbstract weatherResponse) {
        return WeatherEntity.builder()
                .cloud(weatherResponse.getPercentageCloudy() / 100.0)
                .temperature(weatherResponse.getCelsiusTemperature())
                .timestamp(weatherResponse.getTime())
                .windSpeed(weatherResponse.getKphWindSpeed() * 1000 / 3600)
                .humidity(weatherResponse.getPercentageHumidity() / 100.0)
                .pressure(weatherResponse.getMbPressure() * 100)
                .build();
    }
}
