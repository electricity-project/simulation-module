package com.electricity.project.weather;

import com.electricity.project.domains.entity.PowerStation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PowerStationManager {

    public List<PowerStation> getPowerStations(){
        return List.of();
    }
}
