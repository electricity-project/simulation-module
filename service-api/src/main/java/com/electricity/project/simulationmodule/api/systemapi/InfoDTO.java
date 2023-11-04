package com.electricity.project.simulationmodule.api.systemapi;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.Optional;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutableInfoDTO.class)
@JsonDeserialize(as = ImmutableInfoDTO.class)
public interface InfoDTO {

    static ImmutableInfoDTO.Builder builder() {
        return ImmutableInfoDTO.builder();
    }

    @JsonProperty(value = "id")
    Optional<Long> getId();

    @JsonProperty(value = "name", required = true)
    String getName();

    @JsonProperty(value = "ipv4", required = true)
    String getIpv4Address();

    @JsonProperty(value = "state", required = true)
    PowerStationState getState();

    @JsonProperty(value = "creationTime", required = true)
    LocalDateTime getCreationTime();
}
