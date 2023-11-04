package com.electricity.project.simulationmodule.domains.management.powerproduction.entity;

import com.electricity.project.simulationmodule.configuration.Randomizer;
import com.electricity.project.simulationmodule.domains.management.solarpanel.entity.SolarPanel;
import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;

public class SolarPanelProductionTask extends PowerProductionTask<SolarPanel> {

    public SolarPanelProductionTask(SolarPanel powerStation, WeatherEntity weather, PowerProductionTaskUtil util) {
        super(powerStation, weather, util);
    }

    @Override
    protected double calculatePowerProduction() {
        // TODO solar panel power production formula
        return Math.random() * powerStation.getMaxPower();
    }

    @Override
    protected boolean getRandomEvent() {
        // TODO solar panel random event
        return Randomizer.getInstance().nextDouble() < 0.0000001;
    }
}
