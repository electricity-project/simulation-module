package com.electricity.project.simulationmodule.domains.systemapi.control;

import com.electricity.project.simulationmodule.api.systemapi.PowerStationInfoDTO;
import com.electricity.project.simulationmodule.api.systemapi.SolarPanelInfoDTO;
import com.electricity.project.simulationmodule.api.systemapi.WindTurbineInfoDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.management.solarpanel.entity.SolarPanel;
import com.electricity.project.simulationmodule.domains.management.windturbine.entity.WindTurbine;
import com.electricity.project.simulationmodule.domains.systemapi.control.exception.IncorrectPowerStationTypeException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InfoMapper {

    public static PowerStationInfoDTO mapToDTO(PowerStation powerStation) {
        return switch (powerStation) {
            case WindTurbine windTurbine -> mapToDTO(windTurbine);
            case SolarPanel solarPanel -> mapToDTO(solarPanel);
            default -> throw new IncorrectPowerStationTypeException(powerStation);
        };
    }

    public static WindTurbineInfoDTO mapToDTO(WindTurbine windTurbine) {
        return WindTurbineInfoDTO.builder()
                .ipv6Address(windTurbine.getIpv6Address())
                .state(windTurbine.getState())
                .creationTime(windTurbine.getCreationTime())
                .maxPower(windTurbine.getMaxPower())
                .bladeLength(windTurbine.getBladeLength())
                .build();
    }

    public static SolarPanelInfoDTO mapToDTO(SolarPanel solarPanel) {
        return SolarPanelInfoDTO.builder()
                .ipv6Address(solarPanel.getIpv6Address())
                .state(solarPanel.getState())
                .creationTime(solarPanel.getCreationTime())
                .maxPower(solarPanel.getMaxPower())
                .optimalTemperature(solarPanel.getOptimalTemperature())
                .build();
    }
}
