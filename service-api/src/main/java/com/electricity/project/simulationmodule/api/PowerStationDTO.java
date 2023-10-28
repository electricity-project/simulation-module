package com.electricity.project.simulationmodule.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PowerStationDTO {

    @JsonProperty(value = "id", required = false)
    Optional<Long> getId();

    @JsonProperty(value = "name", required = true)
    String getName();

    @JsonProperty(value = "ipv4", required = true)
    String getIpv4Address();

    @JsonProperty(value = "state", required = false)
    Optional<PowerStationState> getState();

    @JsonProperty(value = "creationTime", required = true)
    LocalDateTime getCreationTime();
}
