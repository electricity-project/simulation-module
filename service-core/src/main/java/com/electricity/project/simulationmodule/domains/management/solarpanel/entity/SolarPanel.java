package com.electricity.project.simulationmodule.domains.management.solarpanel.entity;

import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStationType;
import com.electricity.project.simulationmodule.domains.management.powerproduction.entity.PowerProductionTask;
import com.electricity.project.simulationmodule.domains.management.powerproduction.entity.PowerProductionTaskUtil;
import com.electricity.project.simulationmodule.domains.management.powerproduction.entity.SolarPanelProductionTask;
import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class SolarPanel extends PowerStation {

    @Column(nullable = false)
    private double maxPower;

    @Column(nullable = false)
    private double optimalTemperature;

    @Column(nullable = false)
    private double zenithFactorWeight;

    @Column(nullable = false)
    private double cloudFactorWeight;

    @Column(nullable = false)
    private double minPowerCoefficientFactorValue;

    @Column(nullable = false)
    private double maxPowerCoefficientFactorValue;

    @Column(nullable = false)
    private double meanPowerCoefficientFactorValue;

    @Override
    public PowerProductionTask<SolarPanel> createTask(WeatherEntity weatherEntity, PowerProductionTaskUtil util) {
        return new SolarPanelProductionTask(this, weatherEntity, util);
    }

    @Override
    public PowerStationType getType() {
        return PowerStationType.SOLAR_PANEL;
    }
}
