package com.electricity.project.simulationmodule.domains.windturbine.boundary;

import com.electricity.project.simulationmodule.api.WindTurbineDTO;
import com.electricity.project.simulationmodule.domains.windturbine.control.WindTurbineMapper;
import com.electricity.project.simulationmodule.domains.windturbine.control.WindTurbineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wind-turbine")
@RequiredArgsConstructor
public class WindTurbineResource {

    private final WindTurbineService windTurbineService;

    @PostMapping("/connect")
    ResponseEntity<WindTurbineDTO> connectWithNewWindTurbine(@RequestBody WindTurbineDTO windTurbineDTO) {
        return ResponseEntity.ok(WindTurbineMapper.mapToDTO(windTurbineService.connectWithNewWindTurbine(windTurbineDTO)));
    }
}
