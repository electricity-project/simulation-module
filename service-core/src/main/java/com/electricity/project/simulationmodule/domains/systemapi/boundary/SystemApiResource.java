package com.electricity.project.simulationmodule.domains.systemapi.boundary;

import com.electricity.project.simulationmodule.api.error.ImmutableErrorDTO;
import com.electricity.project.simulationmodule.api.systemapi.ImmutableInfoDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationNotExistsException;
import com.electricity.project.simulationmodule.domains.systemapi.control.SystemApiService;
import com.electricity.project.simulationmodule.domains.systemapi.control.exception.IncorrectStateForOperationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class SystemApiResource {
    private final SystemApiService systemApiService;

    @GetMapping("/info")
    public ResponseEntity<ImmutableInfoDTO> getInfo(@RequestParam String ipv4Address) {
        return ResponseEntity.ok(systemApiService.getInfo(ipv4Address));
    }

    @GetMapping("/connect")
    public ResponseEntity<Void> connectToSystem(@RequestParam String ipv4Address) {
        systemApiService.connectToSystem(ipv4Address);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/disconnect")
    public ResponseEntity<Void> disconnectFromSystem(@RequestParam String ipv4Address) {
        systemApiService.disconnectFromSystem(ipv4Address);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/start")
    public ResponseEntity<Void> startPowerStation(@RequestParam String ipv4Address) {
        systemApiService.startPowerStation(ipv4Address);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/stop")
    public ResponseEntity<Void> stopPowerStation(@RequestParam String ipv4Address) {
        systemApiService.stopPowerStation(ipv4Address);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(PowerStationNotExistsException.class)
    private ResponseEntity<Void> handlePowerStationNotExistsException(PowerStationNotExistsException exception) {
        log.error("Power station not found", exception);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IncorrectStateForOperationException.class)
    private ResponseEntity<ImmutableErrorDTO> handleIncorrectStateForOperationException(IncorrectStateForOperationException exception) {
        log.error("Power station in incorrect state for wanted operation", exception);
        return ResponseEntity.badRequest().body(ImmutableErrorDTO.builder().error(exception.getMessage()).build());
    }
}
