package com.electricity.project.simulationmodule.domains.management.powerstation.control;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationState;
import com.electricity.project.simulationmodule.configuration.Randomizer;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationAlreadyDamagedException;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationAlreadyInMaintenanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final PowerStationService powerStationService;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    @Value("${maintenance.random.value.min}")
    private Duration minRandomDuration;
    @Value("${maintenance.random.value.max}")
    private Duration maxRandomDuration;

    public void makeDamage(long powerStationId) {
        PowerStation powerStation = powerStationService.getById(powerStationId);
        validateAndSetDamagedState(powerStation);
    }

    public ZonedDateTime startMaintenance(long powerStationId, Optional<Long> maintenanceDurationMs) {
        PowerStation powerStation = powerStationService.getById(powerStationId);
        validateAndSetMaintenanceState(powerStation);
        long maintenanceDuration = maintenanceDurationMs.orElse(getRandomMaintenanceDuration());
        scheduleMaintenance(powerStation, maintenanceDuration);
        return ZonedDateTime.now(ZoneId.systemDefault()).plusNanos(maintenanceDuration * 1_000_000);
    }

    private void validateAndSetDamagedState(PowerStation powerStation) {
        if (powerStation.getState() == PowerStationState.DAMAGED) {
            throw new PowerStationAlreadyDamagedException(powerStation.getId());
        }
        updatePowerStationState(powerStation, PowerStationState.DAMAGED);
    }

    private void updatePowerStationState(PowerStation powerStation, PowerStationState newState) {
        powerStation.setState(newState);
        powerStationService.save(powerStation);
    }

    private void validateAndSetMaintenanceState(PowerStation powerStation) {
        if (powerStation.getState() == PowerStationState.MAINTENANCE) {
            throw new PowerStationAlreadyInMaintenanceException(powerStation.getId());
        }
        updatePowerStationState(powerStation, PowerStationState.MAINTENANCE);
    }

    private long getRandomMaintenanceDuration() {
        return Randomizer.getInstance().nextLong(minRandomDuration.toMillis(), maxRandomDuration.toMillis() + 1);
    }

    private void scheduleMaintenance(PowerStation powerStation, long maintenanceDuration) {
        Runnable maintenanceTask = createMaintenanceTask(powerStation);
        executor.schedule(maintenanceTask, maintenanceDuration, TimeUnit.MILLISECONDS);
    }

    private Runnable createMaintenanceTask(PowerStation powerStation) {
        return () -> updatePowerStationState(powerStation, PowerStationState.STOPPED);
    }
}
