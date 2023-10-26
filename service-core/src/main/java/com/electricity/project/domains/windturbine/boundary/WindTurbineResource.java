package com.electricity.project.domains.windturbine.boundary;

import com.electricity.project.api.WindTurbineDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/wind-turbine")
public class WindTurbineResource {

    @PostMapping("/connect")
    ResponseEntity<WindTurbineDTO> connectWithNewWindTurbine(@RequestBody WindTurbineDTO windTurbineDTO){
        return ResponseEntity.ok(WindTurbineDTO.builder().from(windTurbineDTO).build());
    }
}
