package com.electricity.project.simulationmodule.domains.sonarpanel.control;

import com.electricity.project.simulationmodule.api.solarpanel.ImmutableInsolationFactorWeightsDTO;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutablePowerCoefficientFactorDTO;
import com.electricity.project.simulationmodule.api.solarpanel.SonarPanelDTO;
import com.electricity.project.simulationmodule.domains.sonarpanel.entity.SonarPanel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SonarPanelMapper {

    public static SonarPanel mapToEntity(SonarPanelDTO sonarPanelDTO) {
        return SonarPanel.builder()
                .id(sonarPanelDTO.getId().orElse(-1L))
                .name(sonarPanelDTO.getName())
                .state(sonarPanelDTO.getState())
                .ipv4Address(sonarPanelDTO.getIpv4Address())
                .createdTime(sonarPanelDTO.getCreationTime())
                .maxPower(sonarPanelDTO.getMaxPower())
                .optimalTemperature(sonarPanelDTO.getOptimalTemperature())
                .minPowerCoefficientFactorValue(sonarPanelDTO.getPowerCoefficientFactor().getMinValue())
                .maxPowerCoefficientFactorValue(sonarPanelDTO.getPowerCoefficientFactor().getMaxValue())
                .meanPowerCoefficientFactorValue(sonarPanelDTO.getPowerCoefficientFactor().getMeanValue())
                .cloudFactorWeight(sonarPanelDTO.getInsolationFactorWeights().getCloudFactorWeight())
                .zenithFactorWeight(sonarPanelDTO.getInsolationFactorWeights().getZenithFactorWeight())
                .build();
    }

    public static SonarPanelDTO mapToDTO(SonarPanel sonarPanel){
        return SonarPanelDTO.builder()
                .id(sonarPanel.getId())
                .name(sonarPanel.getName())
                .ipv4Address(sonarPanel.getIpv4Address())
                .state(sonarPanel.getState())
                .creationTime(sonarPanel.getCreatedTime())
                .maxPower(sonarPanel.getMaxPower())
                .optimalTemperature(sonarPanel.getOptimalTemperature())
                .powerCoefficientFactor(ImmutablePowerCoefficientFactorDTO.builder()
                        .minValue(sonarPanel.getMinPowerCoefficientFactorValue())
                        .maxValue(sonarPanel.getMaxPowerCoefficientFactorValue())
                        .meanValue(sonarPanel.getMeanPowerCoefficientFactorValue())
                        .build())
                .insolationFactorWeights(ImmutableInsolationFactorWeightsDTO.builder()
                        .zenithFactorWeight(sonarPanel.getZenithFactorWeight())
                        .cloudFactorWeight(sonarPanel.getCloudFactorWeight())
                        .build())
                .build();
    }
}
