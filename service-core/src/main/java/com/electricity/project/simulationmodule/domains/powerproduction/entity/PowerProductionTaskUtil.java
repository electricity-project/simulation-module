package com.electricity.project.simulationmodule.domains.powerproduction.entity;

import com.electricity.project.simulationmodule.configuration.PowerProductionGateway;
import com.electricity.project.simulationmodule.domains.power.control.PowerStationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class PowerProductionTaskUtil {
    private final PowerStationService powerStationService;
    private final PowerProductionGateway powerProductionGateway;
    private final ObjectMapper objectMapper;
    @Value("${fixedRate.in.milliseconds}")
    private int fixedRateMs;
}
