package com.electricity.project.simulationmodule.domains.windturbine.control;

import com.electricity.project.simulationmodule.api.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.powerstation.control.PowerStationMapper;
import com.electricity.project.simulationmodule.domains.powerstation.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.windturbine.entity.WindTurbine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WindTurbineMapper {

    public static WindTurbine mapToEntity(WindTurbineDTO windTurbineDTO) {
        return WindTurbine.builder()
                .bladeLength(windTurbineDTO.getBladeLength())
                .minimalEffectivityCoefficient(windTurbineDTO.getMinimalEffectivityCoefficient())
                .maximalEffectivityCoefficient(windTurbineDTO.getMaximalEffectivityCoefficient())
                .powerCoefficient(windTurbineDTO.getPowerCoefficient())
                .powerStation(PowerStationMapper.mapToEntity(windTurbineDTO))
                .build();
    }

    public static WindTurbineDTO mapToDTO(WindTurbine windTurbine){
        PowerStation powerStation = windTurbine.getPowerStation();
        return WindTurbineDTO.builder()
                .id(windTurbine.getId())
                .name(powerStation.getName())
                .ipv4Address(powerStation.getIpv4Address())
                .creationTime(powerStation.getCreatedTime())
                .bladeLength(windTurbine.getBladeLength())
                .minimalEffectivityCoefficient(windTurbine.getMinimalEffectivityCoefficient())
                .maximalEffectivityCoefficient(windTurbine.getMaximalEffectivityCoefficient())
                .powerCoefficient(windTurbine.getPowerCoefficient())
                .build();

    }


}
