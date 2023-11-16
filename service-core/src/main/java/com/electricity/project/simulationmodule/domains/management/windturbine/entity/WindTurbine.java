package com.electricity.project.simulationmodule.domains.management.windturbine.entity;

import com.electricity.project.simulationmodule.domains.management.powerproduction.entity.PowerProductionTask;
import com.electricity.project.simulationmodule.domains.management.powerproduction.entity.PowerProductionTaskUtil;
import com.electricity.project.simulationmodule.domains.management.powerproduction.entity.WindTurbineProductionTask;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
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
    public PowerProductionTask<WindTurbine> createTask(WeatherEntity weatherEntity, PowerProductionTaskUtil util) {
        return new WindTurbineProductionTask(this, weatherEntity, util);
    }
}
