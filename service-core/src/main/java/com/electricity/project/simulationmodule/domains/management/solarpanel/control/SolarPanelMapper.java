package com.electricity.project.simulationmodule.domains.management.solarpanel.control;

import com.electricity.project.simulationmodule.api.solarpanel.InsolationFactorWeightsDTO;
import com.electricity.project.simulationmodule.api.solarpanel.PowerCoefficientFactorDTO;
import com.electricity.project.simulationmodule.api.solarpanel.SolarPanelDTO;
import com.electricity.project.simulationmodule.domains.management.solarpanel.entity.SolarPanel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SolarPanelMapper {

    public static SolarPanel mapToEntity(SolarPanelDTO solarPanelDTO) {
        return SolarPanel.builder()
                .id(solarPanelDTO.getId().orElse(-1L))
                .state(solarPanelDTO.getState())
                .ipv6Address(solarPanelDTO.getIpv6Address())
                .creationTime(solarPanelDTO.getCreationTime())
                .isConnected(solarPanelDTO.isConnected())
                .maxPower(solarPanelDTO.getMaxPower())
                .optimalTemperature(solarPanelDTO.getOptimalTemperature())
                .minPowerCoefficientFactorValue(solarPanelDTO.getPowerCoefficientFactor().getMinValue())
                .maxPowerCoefficientFactorValue(solarPanelDTO.getPowerCoefficientFactor().getMaxValue())
                .meanPowerCoefficientFactorValue(solarPanelDTO.getPowerCoefficientFactor().getMeanValue())
                .cloudFactorWeight(solarPanelDTO.getInsolationFactorWeights().getCloudFactorWeight())
                .zenithFactorWeight(solarPanelDTO.getInsolationFactorWeights().getZenithFactorWeight())
                .build();
    }

    public static SolarPanelDTO mapToDTO(SolarPanel solarPanel){
        return SolarPanelDTO.builder()
                .id(solarPanel.getId())
                .ipv6Address(solarPanel.getIpv6Address())
                .state(solarPanel.getState())
                .creationTime(solarPanel.getCreationTime())
                .isConnected(solarPanel.isConnected())
                .maxPower(solarPanel.getMaxPower())
                .optimalTemperature(solarPanel.getOptimalTemperature())
                .powerCoefficientFactor(PowerCoefficientFactorDTO.builder()
                        .minValue(solarPanel.getMinPowerCoefficientFactorValue())
                        .maxValue(solarPanel.getMaxPowerCoefficientFactorValue())
                        .meanValue(solarPanel.getMeanPowerCoefficientFactorValue())
                        .build())
                .insolationFactorWeights(InsolationFactorWeightsDTO.builder()
                        .zenithFactorWeight(solarPanel.getZenithFactorWeight())
                        .cloudFactorWeight(solarPanel.getCloudFactorWeight())
                        .build())
                .build();
    }
}
