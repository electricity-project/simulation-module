package com.electricity.project.simulationmodule.domains.weather;

import com.electricity.project.simulationmodule.domains.weather.client.WeatherClient;
import com.electricity.project.simulationmodule.domains.weather.control.WeatherMapper;
import com.electricity.project.simulationmodule.domains.weather.control.WeatherService;
import com.electricity.project.simulationmodule.domains.weather.data.WeatherResponseAbstract;
import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import com.electricity.project.simulationmodule.domains.weather.data.Coordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherUpdater {
    private final ExecutorService weatherApiExecutor = Executors.newSingleThreadExecutor();
    private final WeatherClient weatherClient;
    private final WeatherService weatherService;

    public Optional<WeatherEntity> update() {
        Future<WeatherResponseAbstract> weatherResponseFuture = weatherApiExecutor.submit(
                () -> weatherClient.getRealTimeWeather(new Coordinates(17.038538, 51.107883)));

        Optional<WeatherEntity> weatherEntity;
        try {
            weatherEntity = Optional.of(WeatherMapper.mapToEntity(weatherResponseFuture.get(35, TimeUnit.SECONDS)));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("Getting realtime weather error", e);
            weatherEntity = weatherService.getLastWeather();
        }
        return weatherEntity;
    }
}
