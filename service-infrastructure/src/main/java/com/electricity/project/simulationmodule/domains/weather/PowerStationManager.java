package com.electricity.project.simulationmodule.domains.weather;

import com.electricity.project.simulationmodule.domains.powerstation.entity.PowerStation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PowerStationManager {

    public List<PowerStation> getPowerStations(){
        return List.of();
    }
}
