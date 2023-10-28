package com.electricity.project.simulationmodule.domains.weather.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponseWrapper {
    @JsonProperty(required = true)
    private WeatherApiResponse current;
}
