package com.electricity.project.simulationmodule.domains.weather;

import com.electricity.project.simulationmodule.domains.powerstation.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PowerProductionTask implements Runnable{

    private final WeatherEntity weatherEntity;
    private final PowerStation powerStation;
    @Override
    public void run() {
        //Waiting for implementation
    }
}
