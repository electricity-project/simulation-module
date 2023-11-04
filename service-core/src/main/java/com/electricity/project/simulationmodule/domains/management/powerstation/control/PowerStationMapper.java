package com.electricity.project.simulationmodule.domains.management.powerstation.control;

import com.electricity.project.simulationmodule.api.powerstation.ImmutablePowerStationDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PowerStationMapper {

    public static ImmutablePowerStationDTO mapToDTO(PowerStation powerStation){
        return ImmutablePowerStationDTO.builder()
                .id(powerStation.getId())
                .name(powerStation.getName())
                .ipv4Address(powerStation.getIpv4Address())
                .state(powerStation.getState())
                .creationTime(powerStation.getCreationTime())
                .isConnected(powerStation.isConnected())
                .build();
    }
}
