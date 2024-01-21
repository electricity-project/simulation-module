package com.electricity.project.simulationmodule.domains.management.powerproduction.control;

import com.electricity.project.simulationmodule.domains.management.powerproduction.control.exception.WeatherNotFoundException;
import com.electricity.project.simulationmodule.domains.management.powerproduction.entity.PowerProductionTaskUtil;
import com.electricity.project.simulationmodule.domains.weather.WeatherUpdater;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZonedDateTime;

@EnableAsync
@Slf4j
@Component
public class PowerProductionScheduler {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final WeatherUpdater weatherUpdater;
    private final PowerProductionTaskUtil util;

    public PowerProductionScheduler(WeatherUpdater weatherUpdater, PowerProductionTaskUtil util) {
        this.weatherUpdater = weatherUpdater;
        this.util = util;
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
    }

    @Async
    @Scheduled(cron = "10 * * * * ?")
    public void countPowerAndStateForPowerStations() {
        ZonedDateTime messageTimestamp = ZonedDateTime.now();
        weatherUpdater.update().ifPresentOrElse(
                weatherEntity -> util.getPowerStationService()
                        .getAllEntities()
                        .forEach(powerStation ->
                                taskScheduler.schedule(powerStation.createTask(weatherEntity, util, messageTimestamp), Instant.now())),
                () -> {
                    log.error("Cannot find weather data");
                    throw new WeatherNotFoundException();
                });
    }
}
