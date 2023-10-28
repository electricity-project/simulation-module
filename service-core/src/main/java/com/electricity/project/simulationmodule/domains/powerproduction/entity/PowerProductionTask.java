package com.electricity.project.simulationmodule.domains.powerproduction.entity;

import com.electricity.project.simulationmodule.domains.power.entity.PowerStation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class PowerProductionTask<T extends PowerStation> implements Runnable {

    protected T powerStation;

    @Override
    public void run() {
        double v = countPowerProduction();
        log.info("Elektrownia o nazwie: {} o stanie: {}, produkuje: {}", powerStation.getName(), powerStation.getState(), v);
    }

    protected abstract double countPowerProduction();
}
