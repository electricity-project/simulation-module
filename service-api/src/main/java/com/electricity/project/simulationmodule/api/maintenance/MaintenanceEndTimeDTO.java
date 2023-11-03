package com.electricity.project.simulationmodule.api.maintenance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.time.ZonedDateTime;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutableMaintenanceEndTimeDTO.class)
@JsonDeserialize(as = ImmutableMaintenanceEndTimeDTO.class)
public interface MaintenanceEndTimeDTO {
    static ImmutableMaintenanceEndTimeDTO.Builder builder() {
        return ImmutableMaintenanceEndTimeDTO.builder();
    }

    @JsonProperty(value = "endTime", required = true)
    ZonedDateTime getEndTime();
}
