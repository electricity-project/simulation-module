package com.electricity.project.simulationmodule.domains.windturbine.control;

import com.electricity.project.simulationmodule.api.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.powerstation.control.PowerStationRepository;
import com.electricity.project.simulationmodule.domains.windturbine.entity.WindTurbine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WindTurbineService {

    private final WindTurbineRepository windTurbineRepository;
    private final PowerStationRepository powerStationRepository;

    public WindTurbine connectWithNewWindTurbine(WindTurbineDTO windTurbineDTO) {
        WindTurbine windTurbine = WindTurbineMapper.mapToEntity(windTurbineDTO);
        windTurbine.setPowerStation(powerStationRepository.save(windTurbine.getPowerStation()));
        return windTurbineRepository.save(windTurbine);
    }
}
