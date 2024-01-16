package com.electricity.project.simulationmodule.api.solarpanel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style()
@JsonSerialize(as = ImmutablePowerCoefficientFactorDTO.class)
@JsonDeserialize(as = ImmutablePowerCoefficientFactorDTO.class)
public interface PowerCoefficientFactorDTO {

    static ImmutablePowerCoefficientFactorDTO.Builder builder() {
        return ImmutablePowerCoefficientFactorDTO.builder();
    }

    @JsonProperty(value = "minValue", required = true)
    double getMinValue();

    @JsonProperty(value = "maxValue", required = true)
    double getMaxValue();

    @JsonProperty(value = "meanValue", required = true)
    double getMeanValue();
}
