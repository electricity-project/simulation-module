package com.electricity.project.simulationmodule.domains.solarpanel.boundary;

import com.electricity.project.simulationmodule.api.solarpanel.SolarPanelDTO;
import com.electricity.project.simulationmodule.domains.solarpanel.control.SolarPanelMapper;
import com.electricity.project.simulationmodule.domains.solarpanel.control.SolarPanelService;
import com.electricity.project.simulationmodule.domains.solarpanel.control.exception.SolarPanelNotExistsException;
import com.electricity.project.simulationmodule.domains.solarpanel.entity.SolarPanel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/solar-panel")
@RequiredArgsConstructor
@Slf4j
public class SolarPanelResource {

    private final SolarPanelService solarPanelService;

    @GetMapping
    public ResponseEntity<List<SolarPanelDTO>> getAllSolarPanels() {
        return ResponseEntity.ok(solarPanelService.getAllSolarPanels().stream()
                .map(SolarPanelMapper::mapToDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolarPanelDTO> getSolarPanel(@PathVariable("id") long id) {
        return ResponseEntity.ok(SolarPanelMapper.mapToDTO(solarPanelService.getSolarPanelById(id)));
    }

    @PostMapping
    public ResponseEntity<SolarPanelDTO> createNewSolarPanel(@RequestBody SolarPanelDTO solarPanelDTO, UriComponentsBuilder uriBuilder) {
        SolarPanel connectedSolarPanel = solarPanelService.createNewSolarPanel(solarPanelDTO);
        URI location = uriBuilder.path("/{id}")
                .buildAndExpand(connectedSolarPanel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(SolarPanelNotExistsException.class)
    private ResponseEntity<Void> handleSolarPanelNotExistsException(SolarPanelNotExistsException exception) {
        log.error("Solar Panel not found", exception);
        return ResponseEntity.notFound().build();
    }
}
