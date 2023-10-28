package com.electricity.project.simulationmodule.domains.power.control;

import com.electricity.project.simulationmodule.domains.power.entity.PowerStation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PowerStationService {

    private final PowerStationRepository powerStationRepository;

    public List<PowerStation> getAllEntities(){
        return powerStationRepository.findAll();
    }

    public PowerStation save(PowerStation powerStation){
        return powerStationRepository.save(powerStation);
    }
}
