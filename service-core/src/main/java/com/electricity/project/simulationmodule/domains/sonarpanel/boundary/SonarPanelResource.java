package com.electricity.project.simulationmodule.domains.sonarpanel.boundary;

import com.electricity.project.simulationmodule.api.solarpanel.SonarPanelDTO;
import com.electricity.project.simulationmodule.domains.sonarpanel.control.SonarPanelMapper;
import com.electricity.project.simulationmodule.domains.sonarpanel.control.SonarPanelService;
import com.electricity.project.simulationmodule.domains.sonarpanel.control.exception.SonarPanelNotExistsException;
import com.electricity.project.simulationmodule.domains.sonarpanel.entity.SonarPanel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sonar-panel")
@RequiredArgsConstructor
@Slf4j
public class SonarPanelResource {

    private final SonarPanelService sonarPanelService;

    @GetMapping
    public ResponseEntity<List<SonarPanelDTO>> getAllSonarPanels() {
        return ResponseEntity.ok(sonarPanelService.getAllSonarPanels().stream()
                .map(SonarPanelMapper::mapToDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SonarPanelDTO> getSonarPanel(@PathVariable("id") long id) {
        return ResponseEntity.ok(SonarPanelMapper.mapToDTO(sonarPanelService.getSonarPanelById(id)));
    }

    @PostMapping
    public ResponseEntity<SonarPanelDTO> createNewSonarPanel(@RequestBody SonarPanelDTO sonarPanelDTO, UriComponentsBuilder uriBuilder) {
        SonarPanel connectedSonarPanel = sonarPanelService.connectWithNewSonarPanel(sonarPanelDTO);
        URI location = uriBuilder.path("/{id}")
                .buildAndExpand(connectedSonarPanel.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(SonarPanelNotExistsException.class)
    private ResponseEntity<Void> handleSonarPanelNotExistsException(SonarPanelNotExistsException exception) {
        log.error("Sonar Panel not found", exception);
        return ResponseEntity.notFound().build();
    }
}
