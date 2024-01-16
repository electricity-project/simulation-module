package com.electricity.project.simulationmodule.api.systemapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutableWindTurbineInfoDTO.class)
@JsonDeserialize(as = ImmutableWindTurbineInfoDTO.class)
public interface WindTurbineInfoDTO extends PowerStationInfoDTO {

    static ImmutableWindTurbineInfoDTO.Builder builder() {
        return ImmutableWindTurbineInfoDTO.builder();
    }

    @JsonProperty(value = "bladeLength", required = true)
    Long getBladeLength();
}
