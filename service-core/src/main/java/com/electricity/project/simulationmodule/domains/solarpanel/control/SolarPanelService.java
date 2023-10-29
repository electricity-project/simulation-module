package com.electricity.project.simulationmodule.domains.solarpanel.control;

import com.electricity.project.simulationmodule.api.solarpanel.SolarPanelDTO;
import com.electricity.project.simulationmodule.domains.solarpanel.control.exception.SolarPanelNotExistsException;
import com.electricity.project.simulationmodule.domains.solarpanel.entity.SolarPanel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SolarPanelService {

    private final SolarPanelRepository solarPanelRepository;

    public SolarPanel getSolarPanelById(long id) {
        return solarPanelRepository.findById(id).orElseThrow(() -> new SolarPanelNotExistsException(id));
    }

    public SolarPanel connectWithNewSolarPanel(SolarPanelDTO solarPanelDTO) {
        SolarPanel solarPanel = SolarPanelMapper.mapToEntity(solarPanelDTO);
        return solarPanelRepository.save(solarPanel);
    }

    public List<SolarPanel> connectWithNewSolarPanels(List<SolarPanelDTO> solarPanels) {
        return solarPanelRepository.saveAll(solarPanels.stream().map(SolarPanelMapper::mapToEntity).toList());
    }

    public List<SolarPanel> getAllSolarPanels() {
        return solarPanelRepository.findAll();
    }
}
