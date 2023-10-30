package com.electricity.project.simulationmodule.domains.powerproduction.entity;

import com.electricity.project.simulationmodule.api.PowerStationState;
import com.electricity.project.simulationmodule.configuration.Randomizer;
import com.electricity.project.simulationmodule.domains.power.control.PowerStationService;
import com.electricity.project.simulationmodule.domains.power.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class PowerProductionTask<T extends PowerStation> implements Runnable {

    protected T powerStation;

    protected WeatherEntity weather;

    protected PowerStationService powerStationService;

    @Override
    public void run() {
        if (getRandomEvent()) {
            powerStation.setState(PowerStationState.DAMAGED);
            powerStationService.save(powerStation);
        }

        switch (powerStation.getState()) {
            case WORKING -> {
                double producedPower = countPowerProduction();
                log.info("Power station: {}, state: {}, produced: {} MWh", powerStation.getName(), powerStation.getState(), producedPower);
            }
            case DAMAGED, STOPPED ->
                    log.info("Power station: {}, not working, state: {}", powerStation.getName(), powerStation.getState());
        }

    }

    protected abstract double countPowerProduction();

    protected double convertFromWsToMWh(double powerProductionInWatsPerSecond) {
        return powerProductionInWatsPerSecond * 60 * 60 / 1000000;
    }

    protected boolean getRandomEvent() {
        // TODO
        // return (LocalDateTime.now().until(powerStation.getCreatedTime(), ChronoUnit.MILLIS)) < new Random().nextDouble();
        return Randomizer.getInstance().nextBoolean();
    }

}
