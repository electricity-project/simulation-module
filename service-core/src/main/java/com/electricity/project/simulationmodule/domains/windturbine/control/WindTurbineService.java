package com.electricity.project.simulationmodule.domains.windturbine.control;

import com.electricity.project.simulationmodule.api.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.windturbine.entity.WindTurbine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WindTurbineService {

    private final WindTurbineRepository windTurbineRepository;

    public WindTurbine connectWithNewWindTurbine(WindTurbineDTO windTurbineDTO) {
        WindTurbine windTurbine = WindTurbineMapper.mapToEntity(windTurbineDTO);
        return windTurbineRepository.save(windTurbine);
    }

    public List<WindTurbine> connectWithNewWindTurbines(List<WindTurbineDTO> windTurbines) {
        return windTurbineRepository.saveAll(windTurbines.stream().map(WindTurbineMapper::mapToEntity).toList());
    }

}
