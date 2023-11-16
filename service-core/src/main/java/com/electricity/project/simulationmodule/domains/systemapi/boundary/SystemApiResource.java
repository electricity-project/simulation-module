package com.electricity.project.simulationmodule.domains.systemapi.boundary;

import com.electricity.project.simulationmodule.api.error.ErrorDTO;
import com.electricity.project.simulationmodule.api.systemapi.PowerStationInfoDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationNotExistsException;
import com.electricity.project.simulationmodule.domains.systemapi.control.SystemApiService;
import com.electricity.project.simulationmodule.domains.systemapi.control.exception.IncorrectPowerStationTypeException;
import com.electricity.project.simulationmodule.domains.systemapi.control.exception.IncorrectStateForOperationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SystemApiResource {
    private final SystemApiService systemApiService;

    @GetMapping("/info")
    public ResponseEntity<PowerStationInfoDTO> getInfo(@RequestParam String ipv6Address) {
        return ResponseEntity.ok(systemApiService.getInfo(ipv6Address));
    }

    @GetMapping("/connect")
    public ResponseEntity<Void> connectToSystem(@RequestParam String ipv6Address) {
        systemApiService.connectToSystem(ipv6Address);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/disconnect")
    public ResponseEntity<Void> disconnectFromSystem(@RequestParam String ipv6Address) {
        systemApiService.disconnectFromSystem(ipv6Address);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/start")
    public ResponseEntity<Void> startPowerStation(@RequestParam String ipv6Address) {
        systemApiService.startPowerStation(ipv6Address);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stop")
    public ResponseEntity<Void> stopPowerStation(@RequestParam String ipv6Address) {
        systemApiService.stopPowerStation(ipv6Address);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(PowerStationNotExistsException.class)
    private ResponseEntity<Void> handlePowerStationNotExistsException(PowerStationNotExistsException exception) {
        log.error("Power station not found", exception);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IncorrectStateForOperationException.class)
    private ResponseEntity<ErrorDTO> handleIncorrectStateForOperationException(IncorrectStateForOperationException exception) {
        log.error("Power station in incorrect state for wanted operation", exception);
        return ResponseEntity.badRequest().body(ErrorDTO.builder().error(exception.getMessage()).build());
    }

    @ExceptionHandler(IncorrectPowerStationTypeException.class)
    private ResponseEntity<ErrorDTO> handleIncorrectPowerStationTypeException(IncorrectPowerStationTypeException exception) {
        log.error("Incorrect power station type", exception);
        return ResponseEntity.badRequest().body(ErrorDTO.builder().error(exception.getMessage()).build());
    }
}
