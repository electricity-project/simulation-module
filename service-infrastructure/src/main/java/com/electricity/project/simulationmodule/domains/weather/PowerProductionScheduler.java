package com.electricity.project.simulationmodule.domains.weather;

import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@EnableAsync
public class PowerProductionScheduler {

    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private final WeatherUpdater weatherUpdater;
    private final PowerStationManager powerStationManager;

    public PowerProductionScheduler(WeatherUpdater weatherUpdater, PowerStationManager powerStationManager) {
        this.weatherUpdater = weatherUpdater;
        this.powerStationManager = powerStationManager;
        threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        threadPoolTaskScheduler.initialize();
    }

    @Async
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES, fixedRateString = "${fixedRate.in.milliseconds}")
    public void countPowerForPowerStations() {
        WeatherEntity weatherEntity = weatherUpdater.update();
        powerStationManager.getPowerStations().forEach(
                powerStation -> threadPoolTaskScheduler.schedule(new PowerProductionTask(weatherEntity, powerStation), Instant.now()));
    }
}
