package com.electricity.project.simulationmodule.domains.management.windturbine.boundary;

import com.electricity.project.simulationmodule.api.error.ErrorDTO;
import com.electricity.project.simulationmodule.api.windturbine.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.DuplicatedIpv6Exception;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.WindTurbineMapper;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.WindTurbineService;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.exception.WindTurbineNotFoundException;
import com.electricity.project.simulationmodule.domains.management.windturbine.entity.WindTurbine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/wind-turbine")
@RequiredArgsConstructor
@Slf4j
public class WindTurbineResource {

    private final WindTurbineService windTurbineService;

    @GetMapping
    public ResponseEntity<List<WindTurbineDTO>> getAllWindTurbines() {
        return ResponseEntity.ok(windTurbineService.getAllWindTurbines().stream()
                .map(WindTurbineMapper::mapToDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WindTurbineDTO> getWindTurbine(@PathVariable("id") long id) {
        return ResponseEntity.ok(WindTurbineMapper.mapToDTO(windTurbineService.getWindTurbineById(id)));
    }

    @PostMapping
    public ResponseEntity<WindTurbineDTO> connectWithNewWindTurbine(@RequestBody WindTurbineDTO windTurbineDTO, UriComponentsBuilder uriBuilder) {
        WindTurbine windTurbine = windTurbineService.connectWithNewWindTurbine(windTurbineDTO);
        URI location = uriBuilder.path("/{id}")
                .buildAndExpand(windTurbine.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(WindTurbineNotFoundException.class)
    private ResponseEntity<Void> handleWindTurbineNotFoundException(WindTurbineNotFoundException exception) {
        log.error("Wind turbine not found", exception);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DuplicatedIpv6Exception.class)
    private ResponseEntity<ErrorDTO> handleDuplicatedIpv6Exception(DuplicatedIpv6Exception exception) {
        log.error("Duplicated ipv6 address", exception);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorDTO.builder().error(exception.getMessage()).build());
    }
}
