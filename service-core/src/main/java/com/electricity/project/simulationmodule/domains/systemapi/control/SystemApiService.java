package com.electricity.project.simulationmodule.domains.systemapi.control;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationState;
import com.electricity.project.simulationmodule.api.systemapi.PowerStationInfoDTO;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.PowerStationRepository;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationNotExistsException;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.systemapi.control.exception.IncorrectStateForOperationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemApiService {
    private final PowerStationRepository powerStationRepository;

    public void connectToSystem(String ipv6Address) {
        PowerStation powerStation = getPowerStationByIp(ipv6Address);
        powerStation.setConnected(true);
        powerStationRepository.save(powerStation);
    }

    public void disconnectFromSystem(String ipv6Address) {
        PowerStation powerStation = getPowerStationByIp(ipv6Address);
        powerStation.setConnected(false);
        powerStationRepository.save(powerStation);
    }

    public PowerStationInfoDTO getInfo(String ipv6Address) {
        return InfoMapper.mapToDTO(getPowerStationByIp(ipv6Address));
    }

    public void startPowerStation(String ipv6Address) {
        PowerStation powerStation = getPowerStationByIp(ipv6Address);
        if (powerStation.getState() != PowerStationState.STOPPED && powerStation.getState() != PowerStationState.WORKING) {
            throw new IncorrectStateForOperationException(powerStation.getState(), "START");
        }
        powerStation.setState(PowerStationState.WORKING);
        powerStationRepository.save(powerStation);
    }

    public void stopPowerStation(String ipv6Address) {
        PowerStation powerStation = getPowerStationByIp(ipv6Address);
        if (powerStation.getState() != PowerStationState.WORKING && powerStation.getState() != PowerStationState.STOPPED) {
            throw new IncorrectStateForOperationException(powerStation.getState(), "STOP");
        }
        powerStation.setState(PowerStationState.STOPPED);
        powerStationRepository.save(powerStation);
    }

    private PowerStation getPowerStationByIp(String ipv6Address) {
        return powerStationRepository.findFirstByIpv6Address(ipv6Address)
                .orElseThrow(() -> PowerStationNotExistsException.fromIpv6Address(ipv6Address));
    }
}
