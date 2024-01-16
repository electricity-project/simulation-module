package com.electricity.project.simulationmodule.domains.management.powerproduction.entity;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationState;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
public record PowerProductionMessage(
        String ipv6Address,
        PowerStationState state,
        double power,
        ZonedDateTime timestamp
) {
    @Override
    public String toString() {
        return "PowerProductionMessage{" +
                "ipv6Address='" + ipv6Address + '\'' +
                ", state=" + state +
                ", power=" + power +
                ", timestamp=" + timestamp +
                '}';
    }
}
