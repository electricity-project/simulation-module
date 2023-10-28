package com.electricity.project.simulationmodule.api.solarpanel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style()
@JsonSerialize(as = ImmutableInsolationFactorWeightsDTO.class)
@JsonDeserialize(as = ImmutableInsolationFactorWeightsDTO.class)
public interface InsolationFactorWeightsDTO {

    static ImmutableInsolationFactorWeightsDTO.Builder builder() {
        return ImmutableInsolationFactorWeightsDTO.builder();
    }

    @JsonProperty(value = "zenithFactorWeight", required = true)
    double getZenithFactorWeight();

    @JsonProperty(value = "cloudFactorWeight", required = true)
    double getCloudFactorWeight();
}
