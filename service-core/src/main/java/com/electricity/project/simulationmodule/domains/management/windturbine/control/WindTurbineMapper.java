package com.electricity.project.simulationmodule.domains.management.windturbine.control;

import com.electricity.project.simulationmodule.api.windturbine.ImmutableWindTurbineDTO;
import com.electricity.project.simulationmodule.domains.management.windturbine.entity.WindTurbine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WindTurbineMapper {

    public static WindTurbine mapToEntity(ImmutableWindTurbineDTO windTurbineDTO) {
        return WindTurbine.builder()
                .id(windTurbineDTO.getId().orElse(-1L))
                .name(windTurbineDTO.getName())
                .state(windTurbineDTO.getState())
                .ipv4Address(windTurbineDTO.getIpv4Address())
                .creationTime(windTurbineDTO.getCreationTime())
                .isConnected(windTurbineDTO.isConnected())
                .bladeLength(windTurbineDTO.getBladeLength())
                .minimalEffectivityCoefficient(windTurbineDTO.getMinimalEffectivityCoefficient())
                .maximalEffectivityCoefficient(windTurbineDTO.getMaximalEffectivityCoefficient())
                .powerCoefficient(windTurbineDTO.getPowerCoefficient())
                .build();
    }

    public static ImmutableWindTurbineDTO mapToDTO(WindTurbine windTurbine){
        return ImmutableWindTurbineDTO.builder()
                .id(windTurbine.getId())
                .name(windTurbine.getName())
                .ipv4Address(windTurbine.getIpv4Address())
                .state(windTurbine.getState())
                .creationTime(windTurbine.getCreationTime())
                .bladeLength(windTurbine.getBladeLength())
                .minimalEffectivityCoefficient(windTurbine.getMinimalEffectivityCoefficient())
                .maximalEffectivityCoefficient(windTurbine.getMaximalEffectivityCoefficient())
                .powerCoefficient(windTurbine.getPowerCoefficient())
                .build();
    }
}
