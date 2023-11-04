package com.electricity.project.simulationmodule.database;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationState;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutableInsolationFactorWeightsDTO;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutablePowerCoefficientFactorDTO;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutableSolarPanelDTO;
import com.electricity.project.simulationmodule.api.windturbine.ImmutableWindTurbineDTO;
import com.electricity.project.simulationmodule.domains.management.solarpanel.control.SolarPanelService;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.WindTurbineService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitializeDatabase {

    private final WindTurbineService windTurbineService;
    private final SolarPanelService solarPanelService;
    private long ipLongValue = 3232235521L;

    @Value("${database.initialization.wind.turbines.number}")
    private int numberOfWindTurbines;
    @Value("${database.initialization.solar.panels.number}")
    private int numberOfSolarPanels;

    @SneakyThrows
    @PostConstruct
    void initializeDatabase() {
        if (windTurbineService.countWindTurbines() < numberOfWindTurbines) {
            initializeWindTurbines();
        }
        if (solarPanelService.countSolarPanels() < numberOfSolarPanels) {
            initializeSolarPanels();
        }
    }

    @SneakyThrows
    private void initializeWindTurbines() {
        List<ImmutableWindTurbineDTO> windTurbines = new ArrayList<>();

        ImmutableWindTurbineDTO.Builder windTurbineBuilder = ImmutableWindTurbineDTO.builder()
                .bladeLength(30L)
                .creationTime(LocalDateTime.now())
                .state(PowerStationState.WORKING)
                .isConnected(false)
                .minimalEffectivityCoefficient(5L)
                .maximalEffectivityCoefficient(15L)
                .powerCoefficient(20L);

        for (int i = 0; i < numberOfWindTurbines; i++) {
            windTurbines.add(windTurbineBuilder
                    .name("WindTurbine-" + i)
                    .ipv4Address(InetAddress.getByName(String.valueOf(ipLongValue++)).getHostAddress())
                    .build());
        }

        windTurbineService.connectWithNewWindTurbines(windTurbines);
    }

    @SneakyThrows
    private void initializeSolarPanels() {
        List<ImmutableSolarPanelDTO> solarPanels = new ArrayList<>();

        ImmutableSolarPanelDTO.Builder solarPanelBuilder = ImmutableSolarPanelDTO.builder()
                .creationTime(LocalDateTime.now())
                .state(PowerStationState.WORKING)
                .isConnected(false)
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
            solarPanels.add(solarPanelBuilder
                    .name("SolarPanel-" + i)
                    .ipv4Address(InetAddress.getByName(String.valueOf(ipLongValue++)).getHostAddress())
                    .build());
        }

        solarPanelService.createNewSolarPanels(solarPanels);
    }
}
