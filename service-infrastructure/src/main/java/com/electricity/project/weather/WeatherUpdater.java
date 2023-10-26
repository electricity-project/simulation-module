package com.electricity.project.weather;

import com.electricity.project.weather.client.WeatherClient;
import com.electricity.project.weather.control.WeatherMapper;
import com.electricity.project.weather.control.WeatherService;
import com.electricity.project.weather.data.Coordinates;
import com.electricity.project.weather.data.WeatherResponseAbstract;
import com.electricity.project.weather.entity.WeatherEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherUpdater {
    private final ExecutorService weatherApiExecutor = Executors.newSingleThreadExecutor();
    private final WeatherClient weatherClient;
    private final WeatherService weatherService;

    public WeatherEntity update() {
        Future<WeatherResponseAbstract> weatherResponseFuture = weatherApiExecutor.submit(
                () -> weatherClient.getRealTimeWeather(new Coordinates(1.4, 1.4)));

        WeatherEntity weatherEntity;
        try {
            weatherEntity = WeatherMapper.mapToEntity(weatherResponseFuture.get(35, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error("Getting realtime weather error", e);
            weatherEntity = weatherService.getLastWeather();
        }
        return weatherEntity;
    }
}
