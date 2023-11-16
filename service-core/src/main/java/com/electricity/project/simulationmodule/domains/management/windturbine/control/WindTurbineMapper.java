package com.electricity.project.simulationmodule.domains.management.windturbine.control;

import com.electricity.project.simulationmodule.api.windturbine.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.management.windturbine.entity.WindTurbine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WindTurbineMapper {

    public static WindTurbine mapToEntity(WindTurbineDTO windTurbineDTO) {
        return WindTurbine.builder()
                .id(windTurbineDTO.getId().orElse(-1L))
                .state(windTurbineDTO.getState())
                .ipv6Address(windTurbineDTO.getIpv6Address())
                .creationTime(windTurbineDTO.getCreationTime())
                .isConnected(windTurbineDTO.isConnected())
                .bladeLength(windTurbineDTO.getBladeLength())
                .minimalEffectivityCoefficient(windTurbineDTO.getMinimalEffectivityCoefficient())
                .maximalEffectivityCoefficient(windTurbineDTO.getMaximalEffectivityCoefficient())
                .powerCoefficient(windTurbineDTO.getPowerCoefficient())
                .maxPower(1000) // TODO set/calculate real maxPower
                .isConnected(windTurbineDTO.isConnected())
                .build();
    }

    public static WindTurbineDTO mapToDTO(WindTurbine windTurbine) {
        return WindTurbineDTO.builder()
                .id(windTurbine.getId())
                .ipv6Address(windTurbine.getIpv6Address())
                .state(windTurbine.getState())
                .creationTime(windTurbine.getCreationTime())
                .bladeLength(windTurbine.getBladeLength())
                .minimalEffectivityCoefficient(windTurbine.getMinimalEffectivityCoefficient())
                .maximalEffectivityCoefficient(windTurbine.getMaximalEffectivityCoefficient())
                .powerCoefficient(windTurbine.getPowerCoefficient())
                .maxPower(windTurbine.getMaxPower())
                .isConnected(windTurbine.isConnected())
                .build();
    }
}
