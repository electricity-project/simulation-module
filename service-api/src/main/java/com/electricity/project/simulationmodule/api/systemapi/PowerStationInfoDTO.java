package com.electricity.project.simulationmodule.api.systemapi;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDateTime;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutablePowerStationInfoDTO.class)
@JsonDeserialize(as = ImmutablePowerStationInfoDTO.class)
public interface PowerStationInfoDTO {

    static ImmutablePowerStationInfoDTO.Builder builder() {
        return ImmutablePowerStationInfoDTO.builder();
    }

    @JsonProperty(value = "ipv6", required = true)
    String getIpv6Address();

    @JsonProperty(value = "state", required = true)
    PowerStationState getState();

    @JsonProperty(value = "creationTime", required = true)
    LocalDateTime getCreationTime();

    @JsonProperty(value = "maxPower", required = true)
    double getMaxPower();
}
