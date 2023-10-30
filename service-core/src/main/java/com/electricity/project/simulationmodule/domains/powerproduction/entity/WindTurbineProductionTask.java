package com.electricity.project.simulationmodule.domains.powerproduction.entity;

import com.electricity.project.simulationmodule.domains.power.control.PowerStationService;
import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import com.electricity.project.simulationmodule.domains.windturbine.entity.WindTurbine;

public class WindTurbineProductionTask extends PowerProductionTask<WindTurbine> {

    private static final double RD = 287.058;
    private static final double RV = 461.495;

    public WindTurbineProductionTask(WindTurbine powerStation, WeatherEntity weather, PowerStationService powerStationService) {
        this.powerStation = powerStation;
        this.weather = weather;
        this.powerStationService = powerStationService;
    }

    @Override
    protected double countPowerProduction() {
        return convertFromWsToMWh(countPowerProductionPerSecond());
    }

    private double countPowerProductionPerSecond() {
        return 0.5 * countAirDensity() * countWindEnergy() * countAreaOfTurbine();
    }

    private double countAirDensity() {
        double p1 = 6.1078 * Math.pow(10.0, (7.5 * weather.getTemperature()) / (weather.getTemperature() + 237.3));
        double pv = weather.getHumidity() * p1;
        double pd = weather.getPressure() - pv;
        return (pd / (RD * weather.getTemperatureInKelvin())) + (pv / (RV * weather.getTemperatureInKelvin()));
    }

    private double countWindEnergy() {
        double windSpeed = weather.getWindSpeed();

        if (powerStation.getMinimalEffectivityCoefficient() > windSpeed) {
            return 0.0;
        } else if (powerStation.getMaximalEffectivityCoefficient() < windSpeed) {
            return Math.pow(powerStation.getMaximalEffectivityCoefficient(), 3) * powerStation.getPowerCoefficient();
        } else {
            return Math.pow(windSpeed, 3) * powerStation.getPowerCoefficient() / 100;
        }
    }

    private double countAreaOfTurbine() {
        return Math.PI * Math.pow(powerStation.getBladeLength(), 2);
    }
}
