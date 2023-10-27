package com.electricity.project.simulationmodule.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
public interface PowerStationDTO {

    @JsonProperty(value = "id", required = false)
    Long getId();

    @JsonProperty(value = "name", required = true)
    String getName();

    @JsonProperty(value = "ipv4", required = true)
    String getIpv4Address();

    @JsonProperty(value = "creationTime", required = true)
    LocalDateTime getCreationTime();
}
