package com.electricity.project.simulationmodule.domains.management.powerstation.boundary;

import com.electricity.project.simulationmodule.api.error.ErrorDTO;
import com.electricity.project.simulationmodule.api.maintenance.MaintenanceEndTimeDTO;
import com.electricity.project.simulationmodule.api.powerstation.PowerStationDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.MaintenanceService;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.PowerStationMapper;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.PowerStationService;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationAlreadyDamagedException;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationAlreadyInMaintenanceException;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/power-station")
@RequiredArgsConstructor
public class PowerStationResource {
    private final PowerStationService powerStationService;
    private final MaintenanceService maintenanceService;

    @GetMapping
    public ResponseEntity<List<PowerStationDTO>> getAllPowerStations() {
        return ResponseEntity.ok(powerStationService.getAllEntities().stream()
                .map(PowerStationMapper::mapToDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PowerStationDTO> getPowerStation(@PathVariable("id") long id) {
        return ResponseEntity.ok(PowerStationMapper.mapToDTO(powerStationService.getById(id)));
    }

    @GetMapping("/damage")
    public ResponseEntity<Void> makeDamage(@RequestParam long id) {
        maintenanceService.makeDamage(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/maintenance")
    public ResponseEntity<MaintenanceEndTimeDTO> startMaintenance(@RequestParam long id, @RequestParam Optional<Long> durationMs) {
        ZonedDateTime endTime = maintenanceService.startMaintenance(id, durationMs);
        return ResponseEntity.ok(MaintenanceEndTimeDTO.builder().endTime(endTime).build());
    }

    @ExceptionHandler(PowerStationNotExistsException.class)
    private ResponseEntity<Void> handlePowerStationNotExistsException(PowerStationNotExistsException exception) {
        log.error("Power station not found", exception);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PowerStationAlreadyDamagedException.class)
    private ResponseEntity<ErrorDTO> handlePowerStationAlreadyDamagedException(PowerStationAlreadyDamagedException exception) {
        log.error("Power station is damaged", exception);
        return ResponseEntity.badRequest().body(ErrorDTO.builder().error(exception.getMessage()).build());
    }

    @ExceptionHandler(PowerStationAlreadyInMaintenanceException.class)
    private ResponseEntity<ErrorDTO> handlePowerStationAlreadyInMaintenanceException(PowerStationAlreadyInMaintenanceException exception) {
        log.error("Power station is in maintenance mode", exception);
        return ResponseEntity.badRequest().body(ErrorDTO.builder().error(exception.getMessage()).build());
    }
}
