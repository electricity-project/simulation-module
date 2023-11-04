package com.electricity.project.simulationmodule.domains.systemapi.control;

import com.electricity.project.simulationmodule.api.systemapi.ImmutableInfoDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InfoMapper {

    public static ImmutableInfoDTO mapToDTO(PowerStation powerStation){
        return ImmutableInfoDTO.builder()
                .id(powerStation.getId())
                .name(powerStation.getName())
                .ipv4Address(powerStation.getIpv4Address())
                .state(powerStation.getState())
                .creationTime(powerStation.getCreationTime())
                .build();
    }
}
