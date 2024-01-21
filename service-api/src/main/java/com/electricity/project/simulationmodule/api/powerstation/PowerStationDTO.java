package com.electricity.project.simulationmodule.api.powerstation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.ZonedDateTime;
import java.util.Optional;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutablePowerStationDTO.class)
@JsonDeserialize(as = ImmutablePowerStationDTO.class)
public interface PowerStationDTO {

    static ImmutablePowerStationDTO.Builder builder() {
        return ImmutablePowerStationDTO.builder();
    }

    @JsonProperty(value = "id")
    Optional<Long> getId();

    @JsonProperty(value = "ipv6", required = true)
    String getIpv6Address();

    @JsonProperty(value = "state", required = true)
    PowerStationState getState();

    @JsonProperty(value = "creationTime", required = true)
    ZonedDateTime getCreationTime();

    @JsonProperty(value = "isConnected", required = true)
    boolean isConnected();
}
