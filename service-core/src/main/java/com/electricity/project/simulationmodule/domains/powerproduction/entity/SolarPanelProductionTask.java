package com.electricity.project.simulationmodule.domains.powerproduction.entity;

import com.electricity.project.simulationmodule.domains.solarpanel.entity.SolarPanel;
import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;

public class SolarPanelProductionTask extends PowerProductionTask<SolarPanel> {

    public SolarPanelProductionTask(SolarPanel powerStation, WeatherEntity weather) {
        this.powerStation = powerStation;
        this.weather = weather;
    }

    @Override
    protected double countPowerProduction() {
        // TODO solar panel power production formula
        return Math.random() * powerStation.getMaxPower();
    }
}
