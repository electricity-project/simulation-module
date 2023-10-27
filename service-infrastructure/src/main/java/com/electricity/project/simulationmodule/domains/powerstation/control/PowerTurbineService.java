package com.electricity.project.simulationmodule.domains.powerstation.control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PowerTurbineService {

    private final PowerStationRepository windTurbineRepository;


}
