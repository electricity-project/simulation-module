package com.electricity.project.simulationmodule.database;

import com.electricity.project.simulationmodule.api.*;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutableInsolationFactorWeightsDTO;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutablePowerCoefficientFactorDTO;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutableSolarPanelDTO;
import com.electricity.project.simulationmodule.api.solarpanel.SolarPanelDTO;
import com.electricity.project.simulationmodule.api.windturbine.ImmutableWindTurbineDTO;
import com.electricity.project.simulationmodule.api.windturbine.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.solarpanel.control.SolarPanelService;
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
    private final SolarPanelService solarPanelService;

    @Value("${database.initialization.wind.turbines.number}")
    private int numberOfWindTurbines;
    @Value("${database.initialization.solar.panels.number}")
    private int numberOfSolarPanels;

    @PostConstruct
    void initializeDatabase() {
        initializeWindTurbines();
        initializeSolarPanels();
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

    private void initializeSolarPanels() {
        List<SolarPanelDTO> solarPanels = new ArrayList<>();

        ImmutableSolarPanelDTO.Builder solarPanelBuilder = ImmutableSolarPanelDTO.builder()
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

        for (int i = 0; i < numberOfSolarPanels; i++) {
            solarPanels.add(solarPanelBuilder.name("SolarPanel-" + i).build());
        }

        solarPanelService.createNewSolarPanels(solarPanels);
    }
}
