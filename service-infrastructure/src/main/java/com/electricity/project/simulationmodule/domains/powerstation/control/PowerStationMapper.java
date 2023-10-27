package com.electricity.project.simulationmodule.domains.powerstation.control;

import com.electricity.project.simulationmodule.api.PowerStationDTO;
import com.electricity.project.simulationmodule.domains.powerstation.entity.PowerStation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PowerStationMapper {

    public static PowerStation mapToEntity(@NonNull PowerStationDTO powerStationDTO) {
        return PowerStation.builder()
                .id(powerStationDTO.getId())
                .name(powerStationDTO.getName())
                .ipv4Address(powerStationDTO.getIpv4Address())
                .createdTime(powerStationDTO.getCreationTime())
                .build();
    }

}
