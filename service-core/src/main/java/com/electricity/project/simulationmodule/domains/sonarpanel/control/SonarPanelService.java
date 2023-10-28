package com.electricity.project.simulationmodule.domains.sonarpanel.control;

import com.electricity.project.simulationmodule.api.solarpanel.SonarPanelDTO;
import com.electricity.project.simulationmodule.domains.sonarpanel.control.exception.SonarPanelNotExistsException;
import com.electricity.project.simulationmodule.domains.sonarpanel.entity.SonarPanel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SonarPanelService {

    private final SonarPanelRepository sonarPanelRepository;

    public SonarPanel getSonarPanelById(long id) {
        return sonarPanelRepository.findById(id).orElseThrow(() -> new SonarPanelNotExistsException(id));
    }

    public SonarPanel connectWithNewSonarPanel(SonarPanelDTO sonarPanelDTO) {
        SonarPanel sonarPanel = SonarPanelMapper.mapToEntity(sonarPanelDTO);
        return sonarPanelRepository.save(sonarPanel);
    }

    public List<SonarPanel> connectWithNewSonarPanels(List<SonarPanelDTO> sonarPanels) {
        return sonarPanelRepository.saveAll(sonarPanels.stream().map(SonarPanelMapper::mapToEntity).toList());
    }

    public List<SonarPanel> getAllSonarPanels() {
        return sonarPanelRepository.findAll();
    }
}
