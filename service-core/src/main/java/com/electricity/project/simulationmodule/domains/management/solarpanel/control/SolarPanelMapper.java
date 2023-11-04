package com.electricity.project.simulationmodule.domains.management.solarpanel.control;

import com.electricity.project.simulationmodule.api.solarpanel.ImmutableInsolationFactorWeightsDTO;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutablePowerCoefficientFactorDTO;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutableSolarPanelDTO;
import com.electricity.project.simulationmodule.api.solarpanel.SolarPanelDTO;
import com.electricity.project.simulationmodule.domains.management.solarpanel.entity.SolarPanel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SolarPanelMapper {

    public static SolarPanel mapToEntity(ImmutableSolarPanelDTO solarPanelDTO) {
        return SolarPanel.builder()
                .id(solarPanelDTO.getId().orElse(-1L))
                .name(solarPanelDTO.getName())
                .state(solarPanelDTO.getState())
                .ipv4Address(solarPanelDTO.getIpv4Address())
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

    public static ImmutableSolarPanelDTO mapToDTO(SolarPanel solarPanel){
        return SolarPanelDTO.builder()
                .id(solarPanel.getId())
                .name(solarPanel.getName())
                .ipv4Address(solarPanel.getIpv4Address())
                .state(solarPanel.getState())
                .creationTime(solarPanel.getCreationTime())
                .isConnected(solarPanel.isConnected())
                .maxPower(solarPanel.getMaxPower())
                .optimalTemperature(solarPanel.getOptimalTemperature())
                .powerCoefficientFactor(ImmutablePowerCoefficientFactorDTO.builder()
                        .minValue(solarPanel.getMinPowerCoefficientFactorValue())
                        .maxValue(solarPanel.getMaxPowerCoefficientFactorValue())
                        .meanValue(solarPanel.getMeanPowerCoefficientFactorValue())
                        .build())
                .insolationFactorWeights(ImmutableInsolationFactorWeightsDTO.builder()
                        .zenithFactorWeight(solarPanel.getZenithFactorWeight())
                        .cloudFactorWeight(solarPanel.getCloudFactorWeight())
                        .build())
                .build();
    }
}
