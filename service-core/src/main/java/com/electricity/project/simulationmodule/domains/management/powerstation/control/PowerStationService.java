package com.electricity.project.simulationmodule.domains.management.powerstation.control;

import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationNotExistsException;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PowerStationService {

    private final PowerStationRepository powerStationRepository;

    public PowerStation getById(long id){
        return powerStationRepository.findById(id).orElseThrow(() -> new PowerStationNotExistsException(id));
    }

    public List<PowerStation> getAllEntities(){
        return powerStationRepository.findAll();
    }

    public void save(PowerStation powerStation){
        powerStationRepository.save(powerStation);
    }

    public boolean existsAnotherWithTheSameIpv6(PowerStation powerStation) {
        return powerStationRepository.findFirstByIpv6Address(powerStation.getIpv6Address())
                .filter(ps -> !Objects.equals(ps.getId(), powerStation.getId()))
                .isPresent();
    }
}
