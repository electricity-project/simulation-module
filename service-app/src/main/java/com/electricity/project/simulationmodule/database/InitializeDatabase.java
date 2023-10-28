package com.electricity.project.simulationmodule.database;

import com.electricity.project.simulationmodule.api.ImmutableWindTurbineDTO;
import com.electricity.project.simulationmodule.api.PowerStationState;
import com.electricity.project.simulationmodule.api.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.windturbine.control.WindTurbineService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component

public class InitializeDatabase {

    private final WindTurbineService windTurbineService;
    private final int numberOfWindTurbines;

    public InitializeDatabase(
            WindTurbineService windTurbineService,
            @Value("${database.initialization.wind.turbines.number}") int numberOfWindTurbines
    ) {
        this.windTurbineService = windTurbineService;
        this.numberOfWindTurbines = numberOfWindTurbines;
    }

    @PostConstruct
    void initializeDatabase() {
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
}
