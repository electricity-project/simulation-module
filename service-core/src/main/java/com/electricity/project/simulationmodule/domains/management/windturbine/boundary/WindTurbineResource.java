package com.electricity.project.simulationmodule.domains.management.windturbine.boundary;

import com.electricity.project.simulationmodule.api.windturbine.ImmutableWindTurbineDTO;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.WindTurbineService;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.exception.WindTurbineNotFoundException;
import com.electricity.project.simulationmodule.domains.management.windturbine.control.WindTurbineMapper;
import com.electricity.project.simulationmodule.domains.management.windturbine.entity.WindTurbine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    ResponseEntity<List<ImmutableWindTurbineDTO>> getAllWindTurbines() {
        return ResponseEntity.ok(windTurbineService.getAllWindTurbines().stream()
                .map(WindTurbineMapper::mapToDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImmutableWindTurbineDTO> getWindTurbine(@PathVariable("id") long id) {
        return ResponseEntity.ok(WindTurbineMapper.mapToDTO(windTurbineService.getWindTurbineById(id)));
    }

    @PostMapping
    ResponseEntity<ImmutableWindTurbineDTO> connectWithNewWindTurbine(@RequestBody ImmutableWindTurbineDTO windTurbineDTO, UriComponentsBuilder uriBuilder) {
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
}
