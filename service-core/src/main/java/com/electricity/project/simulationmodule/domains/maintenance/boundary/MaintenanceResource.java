package com.electricity.project.simulationmodule.domains.maintenance.boundary;

import com.electricity.project.simulationmodule.api.error.ImmutableErrorDTO;
import com.electricity.project.simulationmodule.api.maintenance.ImmutableMaintenanceEndTimeDTO;
import com.electricity.project.simulationmodule.domains.maintenance.control.MaintenanceService;
import com.electricity.project.simulationmodule.domains.maintenance.control.exception.PowerStationAlreadyInMaintenanceException;
import com.electricity.project.simulationmodule.domains.power.control.exception.PowerStationNotExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceResource {
    private final MaintenanceService maintenanceService;

    @GetMapping
    public ResponseEntity<ImmutableMaintenanceEndTimeDTO> startMaintenance(@RequestParam long id, @RequestParam Optional<Long> durationMs) {
        ZonedDateTime endTime = maintenanceService.startMaintenance(id, durationMs);
        return ResponseEntity.ok(ImmutableMaintenanceEndTimeDTO.builder().endTime(endTime).build());
    }

    @ExceptionHandler(PowerStationNotExistsException.class)
    private ResponseEntity<Void> handleSolarPanelNotExistsException(PowerStationNotExistsException exception) {
        log.error("Power station not found", exception);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(PowerStationAlreadyInMaintenanceException.class)
    private ResponseEntity<ImmutableErrorDTO> handleSolarPanelNotExistsException(PowerStationAlreadyInMaintenanceException exception) {
        log.error("Power station is in maintenance mode", exception);
        return ResponseEntity.badRequest().body(ImmutableErrorDTO.builder().error(exception.getMessage()).build());
    }
}
