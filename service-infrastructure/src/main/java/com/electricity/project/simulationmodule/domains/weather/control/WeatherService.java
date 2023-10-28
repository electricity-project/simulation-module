package com.electricity.project.simulationmodule.domains.weather.control;

import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;

    public Optional<WeatherEntity> getLastWeather() {
        return weatherRepository.findFirstByOrderByTimestampDesc();
    }

    public void addNewWeather(WeatherEntity newWeather) {
        weatherRepository.save(newWeather);
    }
}
