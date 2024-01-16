package com.electricity.project.simulationmodule.domains.management.solarpanel.control;

import com.electricity.project.simulationmodule.api.solarpanel.SolarPanelDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.PowerStationService;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.DuplicatedIpv6Exception;
import com.electricity.project.simulationmodule.domains.management.solarpanel.control.exception.SolarPanelNotExistsException;
import com.electricity.project.simulationmodule.domains.management.solarpanel.entity.SolarPanel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolarPanelService {

    private final SolarPanelRepository solarPanelRepository;
    private final PowerStationService powerStationService;

    public SolarPanel getSolarPanelById(long id) {
        return solarPanelRepository.findById(id).orElseThrow(() -> new SolarPanelNotExistsException(id));
    }

    public SolarPanel createNewSolarPanel(SolarPanelDTO solarPanelDTO) {
        SolarPanel solarPanel = SolarPanelMapper.mapToEntity(solarPanelDTO);
        if (powerStationService.existsAnotherWithTheSameIpv6(solarPanel)) {
            throw new DuplicatedIpv6Exception(solarPanel.getIpv6Address());
        }
        return solarPanelRepository.save(solarPanel);
    }

    public void createNewSolarPanels(List<SolarPanelDTO> solarPanels) {
        solarPanelRepository.saveAll(solarPanels.stream().map(SolarPanelMapper::mapToEntity).toList());
    }

    public List<SolarPanel> getAllSolarPanels() {
        return solarPanelRepository.findAll();
    }

    public long countSolarPanels() {
        return solarPanelRepository.count();
    }
}
