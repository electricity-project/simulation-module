package com.electricity.project.simulationmodule.domains.powerproduction.entity;

import com.electricity.project.simulationmodule.domains.sonarpanel.entity.SonarPanel;

public class SonarPanelProductionTask extends PowerProductionTask<SonarPanel> {

    public SonarPanelProductionTask(SonarPanel powerStation) {
        this.powerStation = powerStation;
    }

    @Override
    protected double countPowerProduction() {
        // TODO sonar panel power production formula
        return Math.random() * powerStation.getMaxPower();
    }
}
