package com.electricity.project.simulationmodule.domains.windturbine.control;

import com.electricity.project.simulationmodule.api.WindTurbineDTO;

import com.electricity.project.simulationmodule.api.PowerStationState;
import com.electricity.project.simulationmodule.domains.windturbine.entity.WindTurbine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WindTurbineMapper {

    public static WindTurbine mapToEntity(WindTurbineDTO windTurbineDTO) {
        return WindTurbine.builder()
                .id(windTurbineDTO.getId().orElse(-1L))
                .name(windTurbineDTO.getName())
                .state(windTurbineDTO.getState().orElse(PowerStationState.WORKING))
                .ipv4Address(windTurbineDTO.getIpv4Address())
                .createdTime(windTurbineDTO.getCreationTime())
                .bladeLength(windTurbineDTO.getBladeLength())
                .minimalEffectivityCoefficient(windTurbineDTO.getMinimalEffectivityCoefficient())
                .maximalEffectivityCoefficient(windTurbineDTO.getMaximalEffectivityCoefficient())
                .powerCoefficient(windTurbineDTO.getPowerCoefficient())
                .build();
    }

    public static WindTurbineDTO mapToDTO(WindTurbine windTurbine){
        return WindTurbineDTO.builder()
                .id(windTurbine.getId())
                .name(windTurbine.getName())
                .ipv4Address(windTurbine.getIpv4Address())
                .creationTime(windTurbine.getCreatedTime())
                .bladeLength(windTurbine.getBladeLength())
                .minimalEffectivityCoefficient(windTurbine.getMinimalEffectivityCoefficient())
                .maximalEffectivityCoefficient(windTurbine.getMaximalEffectivityCoefficient())
                .powerCoefficient(windTurbine.getPowerCoefficient())
                .build();
    }

}
