package com.electricity.project.simulationmodule.domains.powerproduction.entity;

import com.electricity.project.simulationmodule.domains.windturbine.entity.WindTurbine;

public class WindTurbineProductionTask extends PowerProductionTask<WindTurbine> {

    public WindTurbineProductionTask(WindTurbine powerStation) {
        this.powerStation = powerStation;
    }

    @Override
    protected double countPowerProduction() {
        return Math.pow(powerStation.getBladeLength(), 2);
    }
}
