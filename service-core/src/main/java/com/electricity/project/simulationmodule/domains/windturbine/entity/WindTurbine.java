package com.electricity.project.simulationmodule.domains.windturbine.entity;

import com.electricity.project.simulationmodule.domains.power.control.PowerStationService;
import com.electricity.project.simulationmodule.domains.power.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.powerproduction.entity.PowerProductionTask;
import com.electricity.project.simulationmodule.domains.powerproduction.entity.WindTurbineProductionTask;
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
public class WindTurbine extends PowerStation {

    @Column(nullable = false)
    private long bladeLength;

    @Column(nullable = false)
    private long minimalEffectivityCoefficient;

    @Column(nullable = false)
    private long maximalEffectivityCoefficient;

    @Column(nullable = false)
    private long powerCoefficient;


    @Override
    public PowerProductionTask<WindTurbine> createTask(WeatherEntity weatherEntity, PowerStationService powerStationService) {
        return new WindTurbineProductionTask(this, weatherEntity, powerStationService);
    }
}
