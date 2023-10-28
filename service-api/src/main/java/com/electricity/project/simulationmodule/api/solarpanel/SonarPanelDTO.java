package com.electricity.project.simulationmodule.api.solarpanel;

import com.electricity.project.simulationmodule.api.PowerStationDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style()
@JsonSerialize(as = ImmutableSonarPanelDTO.class)
@JsonDeserialize(as = ImmutableSonarPanelDTO.class)
public interface SonarPanelDTO extends PowerStationDTO {

    static ImmutableSonarPanelDTO.Builder builder() {
        return ImmutableSonarPanelDTO.builder();
    }

    @JsonProperty(value = "maxPower", required = true)
    double getMaxPower();

    @JsonProperty(value = "optimalTemperature", required = true)
    double getOptimalTemperature();

    @JsonProperty(value = "insolationFactorWeights", required = true)
    ImmutableInsolationFactorWeightsDTO getInsolationFactorWeights();

    @JsonProperty(value = "powerCoefficientFactor", required = true)
    ImmutablePowerCoefficientFactorDTO getPowerCoefficientFactor();
}
