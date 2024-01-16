package com.electricity.project.simulationmodule.domains.management.windturbine.control;

import com.electricity.project.simulationmodule.api.windturbine.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.PowerStationService;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.DuplicatedIpv6Exception;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.exception.WindTurbineNotFoundException;
import com.electricity.project.simulationmodule.domains.management.windturbine.entity.WindTurbine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WindTurbineService {

    private final WindTurbineRepository windTurbineRepository;
    private final PowerStationService powerStationService;

    public List<WindTurbine> getAllWindTurbines() {
        return windTurbineRepository.findAll();
    }

    public WindTurbine getWindTurbineById(long id){
        return windTurbineRepository.findById(id).orElseThrow(() -> new WindTurbineNotFoundException(id));
    }

    public WindTurbine connectWithNewWindTurbine(WindTurbineDTO windTurbineDTO) {
        WindTurbine windTurbine = WindTurbineMapper.mapToEntity(windTurbineDTO);
        if (powerStationService.existsAnotherWithTheSameIpv6(windTurbine)) {
            throw new DuplicatedIpv6Exception(windTurbine.getIpv6Address());
        }
        return windTurbineRepository.save(windTurbine);
    }

    public void connectWithNewWindTurbines(List<WindTurbineDTO> windTurbines) {
        windTurbineRepository.saveAll(windTurbines.stream().map(WindTurbineMapper::mapToEntity).toList());
    }

    public long countWindTurbines() {
        return windTurbineRepository.count();
    }
}
