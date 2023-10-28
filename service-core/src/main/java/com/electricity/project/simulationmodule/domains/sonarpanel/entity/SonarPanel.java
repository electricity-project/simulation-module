package com.electricity.project.simulationmodule.domains.sonarpanel.entity;

import com.electricity.project.simulationmodule.domains.power.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.powerproduction.entity.PowerProductionTask;
import com.electricity.project.simulationmodule.domains.powerproduction.entity.SonarPanelProductionTask;
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
public class SonarPanel extends PowerStation {

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
    public PowerProductionTask<SonarPanel> createTask() {
        return new SonarPanelProductionTask(this);
    }
}
