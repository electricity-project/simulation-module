package com.electricity.project.simulationmodule.domains.powerproduction.control;

import com.electricity.project.simulationmodule.domains.power.control.PowerStationService;
import com.electricity.project.simulationmodule.domains.powerproduction.control.exception.WeatherNotFoundException;
import com.electricity.project.simulationmodule.domains.weather.WeatherUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;

@EnableAsync
@Slf4j
@Component
public class PowerProductionScheduler {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final WeatherUpdater weatherUpdater;
    private final PowerStationService powerStationService;

    public PowerProductionScheduler(WeatherUpdater weatherUpdater, PowerStationService powerStationService) {
        this.weatherUpdater = weatherUpdater;
        this.powerStationService = powerStationService;
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
    }

    @Async
    @Scheduled(/*fixedRate = 1, timeUnit = TimeUnit.MINUTES,*/ fixedRateString = "${fixedRate.in.milliseconds}")
    public void countPowerForPowerStations() {
        weatherUpdater.update().ifPresentOrElse(
                weatherEntity -> powerStationService
                        .getAllEntities()
                        .forEach(powerStation -> taskScheduler.schedule(powerStation.createTask(weatherEntity), Instant.now())),
                () -> {
                    log.error("Cannot find weather data");
                    throw new WeatherNotFoundException();
                }
        );

    }
}
