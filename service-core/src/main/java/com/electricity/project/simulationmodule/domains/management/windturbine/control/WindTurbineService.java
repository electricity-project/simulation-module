package com.electricity.project.simulationmodule.domains.management.windturbine.control;

import com.electricity.project.simulationmodule.api.windturbine.ImmutableWindTurbineDTO;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.exception.WindTurbineNotFoundException;
import com.electricity.project.simulationmodule.domains.management.windturbine.entity.WindTurbine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WindTurbineService {

    private final WindTurbineRepository windTurbineRepository;

    public List<WindTurbine> getAllWindTurbines() {
        return windTurbineRepository.findAll();
    }

    public WindTurbine getWindTurbineById(long id){
        return windTurbineRepository.findById(id).orElseThrow(() -> new WindTurbineNotFoundException(id));
    }

    public WindTurbine connectWithNewWindTurbine(ImmutableWindTurbineDTO windTurbineDTO) {
        WindTurbine windTurbine = WindTurbineMapper.mapToEntity(windTurbineDTO);
        return windTurbineRepository.save(windTurbine);
    }

    public void connectWithNewWindTurbines(List<ImmutableWindTurbineDTO> windTurbines) {
        windTurbineRepository.saveAll(windTurbines.stream().map(WindTurbineMapper::mapToEntity).toList());
    }

    public long countWindTurbines() {
        return windTurbineRepository.count();
    }
}
