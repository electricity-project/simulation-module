package com.electricity.project.simulationmodule.database;

import com.electricity.project.simulationmodule.api.*;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutableInsolationFactorWeightsDTO;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutablePowerCoefficientFactorDTO;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutableSonarPanelDTO;
import com.electricity.project.simulationmodule.api.solarpanel.SonarPanelDTO;
import com.electricity.project.simulationmodule.api.windturbine.ImmutableWindTurbineDTO;
import com.electricity.project.simulationmodule.api.windturbine.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.sonarpanel.control.SonarPanelService;
import com.electricity.project.simulationmodule.domains.windturbine.control.WindTurbineService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitializeDatabase {

    private final WindTurbineService windTurbineService;
    private final SonarPanelService sonarPanelService;

    @Value("${database.initialization.wind.turbines.number}")
    private int numberOfWindTurbines;
    @Value("${database.initialization.sonar.panels.number}")
    private int numberOfSonarPanels;

    @PostConstruct
    void initializeDatabase() {
        initializeWindTurbines();
        initializeSonarPanels();
    }

    private void initializeWindTurbines() {
        List<WindTurbineDTO> windTurbines = new ArrayList<>();

        ImmutableWindTurbineDTO.Builder windTurbineBuilder = ImmutableWindTurbineDTO.builder()
                .bladeLength(30L)
                .creationTime(LocalDateTime.now())
                .ipv4Address("192.168.0.1")
                .state(PowerStationState.WORKING)
                .minimalEffectivityCoefficient(5L)
                .maximalEffectivityCoefficient(15L)
                .powerCoefficient(20L);

        for (int i = 0; i < numberOfWindTurbines; i++) {
            windTurbines.add(windTurbineBuilder.name("Wiatrak-" + i).build());
        }

        windTurbineService.connectWithNewWindTurbines(windTurbines);
    }

    private void initializeSonarPanels() {
        List<SonarPanelDTO> sonarPanels = new ArrayList<>();

        ImmutableSonarPanelDTO.Builder sonarPanelBuilder = ImmutableSonarPanelDTO.builder()
                .creationTime(LocalDateTime.now())
                .ipv4Address("192.168.0.2")
                .state(PowerStationState.WORKING)
                .maxPower(100)
                .optimalTemperature(25)
                .insolationFactorWeights(ImmutableInsolationFactorWeightsDTO.builder()
                        .cloudFactorWeight(2)
                        .zenithFactorWeight(1)
                        .build())
                .powerCoefficientFactor(ImmutablePowerCoefficientFactorDTO.builder()
                        .minValue(0.2)
                        .maxValue(0.9)
                        .meanValue(0.75)
                        .build());

        for (int i = 0; i < numberOfSonarPanels; i++) {
            sonarPanels.add(sonarPanelBuilder.name("SonarPanel-" + i).build());
        }

        sonarPanelService.connectWithNewSonarPanels(sonarPanels);
    }
}
