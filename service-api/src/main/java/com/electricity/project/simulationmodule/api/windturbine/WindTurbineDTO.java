package com.electricity.project.simulationmodule.api.windturbine;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@Value.Style()
@JsonSerialize(as = ImmutableWindTurbineDTO.class)
@JsonDeserialize(as = ImmutableWindTurbineDTO.class)
public interface WindTurbineDTO extends PowerStationDTO {

    static ImmutableWindTurbineDTO.Builder builder() {
        return ImmutableWindTurbineDTO.builder();
    }

    @JsonProperty(value = "bladeLength", required = true)
    Long getBladeLength();

    @JsonProperty(value = "minimalEffectivityCoefficient", required = true)
    Long getMinimalEffectivityCoefficient();

    @JsonProperty(value = "maximalEffectivityCoefficient", required = true)
    Long getMaximalEffectivityCoefficient();

    @JsonProperty(value = "powerCoefficient", required = true)
    Long getPowerCoefficient();

    @JsonProperty(value = "maxPower")
    Optional<Double> getMaxPower();
}
