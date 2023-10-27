package com.electricity.project.simulationmodule.domains.weather.control;

import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import com.electricity.project.simulationmodule.domains.weather.data.WeatherResponseAbstract;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
