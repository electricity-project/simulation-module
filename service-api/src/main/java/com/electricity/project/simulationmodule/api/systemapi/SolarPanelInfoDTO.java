package com.electricity.project.simulationmodule.api.systemapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutableSolarPanelInfoDTO.class)
@JsonDeserialize(as = ImmutableSolarPanelInfoDTO.class)
public interface SolarPanelInfoDTO extends PowerStationInfoDTO {

    static ImmutableSolarPanelInfoDTO.Builder builder() {
        return ImmutableSolarPanelInfoDTO.builder();
    }

    @JsonProperty(value = "optimalTemperature", required = true)
    double getOptimalTemperature();
}
