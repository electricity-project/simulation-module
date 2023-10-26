package com.electricity.project.weather;

import com.electricity.project.domains.entity.PowerStation;
import com.electricity.project.weather.entity.WeatherEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PowerProductionTask implements Runnable{

    private final WeatherEntity weatherEntity;
    private final PowerStation powerStation;
    @Override
    public void run() {

    }
}
