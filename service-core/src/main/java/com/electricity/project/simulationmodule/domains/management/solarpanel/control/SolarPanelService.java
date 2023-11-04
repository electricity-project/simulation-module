package com.electricity.project.simulationmodule.domains.management.solarpanel.control;

import com.electricity.project.simulationmodule.api.solarpanel.ImmutableSolarPanelDTO;
import com.electricity.project.simulationmodule.domains.management.solarpanel.control.exception.SolarPanelNotExistsException;
import com.electricity.project.simulationmodule.domains.management.solarpanel.entity.SolarPanel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolarPanelService {

    private final SolarPanelRepository solarPanelRepository;

    public SolarPanel getSolarPanelById(long id) {
        return solarPanelRepository.findById(id).orElseThrow(() -> new SolarPanelNotExistsException(id));
    }

    public SolarPanel createNewSolarPanel(ImmutableSolarPanelDTO solarPanelDTO) {
        SolarPanel solarPanel = SolarPanelMapper.mapToEntity(solarPanelDTO);
        return solarPanelRepository.save(solarPanel);
    }

    public void createNewSolarPanels(List<ImmutableSolarPanelDTO> solarPanels) {
        solarPanelRepository.saveAll(solarPanels.stream().map(SolarPanelMapper::mapToEntity).toList());
    }

    public List<SolarPanel> getAllSolarPanels() {
        return solarPanelRepository.findAll();
    }

    public long countSolarPanels() {
        return solarPanelRepository.count();
    }
}
