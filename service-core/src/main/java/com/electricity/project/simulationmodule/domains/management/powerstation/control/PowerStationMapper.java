package com.electricity.project.simulationmodule.domains.management.powerstation.control;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PowerStationMapper {

    public static PowerStationDTO mapToDTO(PowerStation powerStation){
        return PowerStationDTO.builder()
                .id(powerStation.getId())
                .ipv6Address(powerStation.getIpv6Address())
                .state(powerStation.getState())
                .creationTime(powerStation.getCreationTime())
                .isConnected(powerStation.isConnected())
                .build();
    }
}
