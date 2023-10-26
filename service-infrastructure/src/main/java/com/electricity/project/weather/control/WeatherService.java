package com.electricity.project.weather.control;

import com.electricity.project.weather.entity.WeatherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;

    public WeatherEntity getLastWeather() {
        return weatherRepository.getFirstByOrderByTimestampDesc();
    }

    public void addNewWeather(WeatherEntity newWeather) {
        weatherRepository.save(newWeather);
    }
}
