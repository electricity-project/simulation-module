package com.electricity.project.simulationmodule.domains.powerproduction.entity;

import com.electricity.project.simulationmodule.domains.sonarpanel.entity.SonarPanel;
import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;

public class SonarPanelProductionTask extends PowerProductionTask<SonarPanel> {

    public SonarPanelProductionTask(SonarPanel powerStation, WeatherEntity weather) {
        this.powerStation = powerStation;
        this.weather = weather;
    }

    @Override
    protected double countPowerProduction() {
        // TODO sonar panel power production formula
        return Math.random() * powerStation.getMaxPower();
    }
}
