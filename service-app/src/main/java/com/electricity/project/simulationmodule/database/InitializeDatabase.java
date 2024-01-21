package com.electricity.project.simulationmodule.database;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationState;
import com.electricity.project.simulationmodule.api.solarpanel.ImmutableSolarPanelDTO;
import com.electricity.project.simulationmodule.api.solarpanel.InsolationFactorWeightsDTO;
import com.electricity.project.simulationmodule.api.solarpanel.PowerCoefficientFactorDTO;
import com.electricity.project.simulationmodule.api.solarpanel.SolarPanelDTO;
import com.electricity.project.simulationmodule.api.windturbine.ImmutableWindTurbineDTO;
import com.electricity.project.simulationmodule.api.windturbine.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.management.solarpanel.control.SolarPanelService;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.WindTurbineService;
import inet.ipaddr.IPAddressString;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitializeDatabase {

    private final WindTurbineService windTurbineService;
    private final SolarPanelService solarPanelService;
    private BigInteger baseIp = BigInteger.ONE;

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
        List<WindTurbineDTO> windTurbines = new ArrayList<>();

        ImmutableWindTurbineDTO.Builder windTurbineBuilder = WindTurbineDTO.builder()
                .bladeLength(30L)
                .creationTime(ZonedDateTime.now())
                .state(PowerStationState.WORKING)
                .isConnected(false)
                .minimalEffectivityCoefficient(5L)
                .maximalEffectivityCoefficient(15L)
                .powerCoefficient(20L);

        for (int i = 0; i < numberOfWindTurbines; i++) {
            windTurbines.add(windTurbineBuilder.ipv6Address(calculateNextIp()).build());
        }

        windTurbineService.connectWithNewWindTurbines(windTurbines);
    }

    @SneakyThrows
    private void initializeSolarPanels() {
        List<SolarPanelDTO> solarPanels = new ArrayList<>();

        ImmutableSolarPanelDTO.Builder solarPanelBuilder = SolarPanelDTO.builder()
                .creationTime(ZonedDateTime.now())
                .state(PowerStationState.WORKING)
                .isConnected(false)
                .maxPower(100)
                .optimalTemperature(25)
                .insolationFactorWeights(InsolationFactorWeightsDTO.builder()
                        .cloudFactorWeight(2)
                        .zenithFactorWeight(1)
                        .build())
                .powerCoefficientFactor(PowerCoefficientFactorDTO.builder()
                        .minValue(0.2)
                        .maxValue(0.9)
                        .meanValue(0.75)
                        .build());

        for (int i = 0; i < numberOfSolarPanels; i++) {
            solarPanels.add(solarPanelBuilder.ipv6Address(calculateNextIp()).build());
        }

        solarPanelService.createNewSolarPanels(solarPanels);
    }

    private String calculateNextIp() {
        String baseIpString = baseIp.toString(16);
        int len = baseIpString.length();
        if(len < 32) {
            // 32 zeros
            baseIpString = "00000000000000000000000000000000".substring(len) + baseIpString;
        }
        IPAddressString newIpAddress = new IPAddressString(baseIpString);
        String newIp = newIpAddress.getAddress().toFullString();
        baseIp = baseIp.add(BigInteger.ONE);
        return newIp;
    }
}
