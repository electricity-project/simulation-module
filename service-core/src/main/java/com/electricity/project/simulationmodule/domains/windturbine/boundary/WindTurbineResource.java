package com.electricity.project.simulationmodule.domains.windturbine.boundary;

import com.electricity.project.simulationmodule.api.windturbine.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.sonarpanel.control.exception.SonarPanelNotExistsException;
import com.electricity.project.simulationmodule.domains.windturbine.control.WindTurbineMapper;
import com.electricity.project.simulationmodule.domains.windturbine.control.exception.WindTurbineNotFoundException;
import com.electricity.project.simulationmodule.domains.windturbine.control.WindTurbineService;
import com.electricity.project.simulationmodule.domains.windturbine.entity.WindTurbine;
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
    ResponseEntity<List<WindTurbineDTO>> getAllWindTurbines() {
        return ResponseEntity.ok(windTurbineService.getAllWindTurbines().stream()
                .map(WindTurbineMapper::mapToDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WindTurbineDTO> getWindTurbine(@PathVariable("id") long id) {
        return ResponseEntity.ok(WindTurbineMapper.mapToDTO(windTurbineService.getWindTurbineById(id)));
    }

    @PostMapping
    ResponseEntity<WindTurbineDTO> connectWithNewWindTurbine(@RequestBody WindTurbineDTO windTurbineDTO, UriComponentsBuilder uriBuilder) {
        WindTurbine windTurbine = windTurbineService.connectWithNewWindTurbine(windTurbineDTO);
        URI location = uriBuilder.path("/{id}")
                .buildAndExpand(windTurbine.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(WindTurbineNotFoundException.class)
    private ResponseEntity<Void> handleWindTurbineNotFoundException(SonarPanelNotExistsException exception) {
        log.error("Wind turbine not found", exception);
        return ResponseEntity.notFound().build();
    }
}
