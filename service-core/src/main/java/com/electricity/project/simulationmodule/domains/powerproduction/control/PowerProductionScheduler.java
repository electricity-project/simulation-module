package com.electricity.project.simulationmodule.domains.powerproduction.control;

import com.electricity.project.simulationmodule.domains.powerproduction.control.exception.WeatherNotFoundException;
import com.electricity.project.simulationmodule.domains.powerproduction.entity.PowerProductionTaskUtil;
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
    private final PowerProductionTaskUtil util;

    public PowerProductionScheduler(WeatherUpdater weatherUpdater, PowerProductionTaskUtil util) {
        this.weatherUpdater = weatherUpdater;
        this.util = util;
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
    }

    @Async
    @Scheduled(/*fixedRate = 1, timeUnit = TimeUnit.MINUTES,*/ fixedRateString = "${fixedRate.in.milliseconds}")
    public void countPowerAndStateForPowerStations() {
        weatherUpdater.update().ifPresentOrElse(
                weatherEntity -> util.getPowerStationService()
                        .getAllEntities()
                        .forEach(powerStation ->
                                taskScheduler.schedule(powerStation.createTask(weatherEntity, util), Instant.now())),
                () -> {
                    log.error("Cannot find weather data");
                    throw new WeatherNotFoundException();
                });
    }
}
