package com.electricity.project.simulationmodule.domains.powerproduction.entity;

import com.electricity.project.simulationmodule.api.PowerStationState;
import com.electricity.project.simulationmodule.domains.power.entity.PowerStationType;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record PowerProductionMessage(
        String id,
        String ipv4Address,
        ZonedDateTime timestamp,
        double power,
        PowerStationState state,
        PowerStationType type,
        ZonedDateTime createdTime
) { }
