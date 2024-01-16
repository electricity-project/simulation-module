package com.electricity.project.simulationmodule.domains.management.solarpanel.control.exception;

import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationNotExistsException;

public class SolarPanelNotExistsException extends PowerStationNotExistsException {
    public SolarPanelNotExistsException(long id) {
        super("Solar Panel with id = %d not exists".formatted(id));
    }
}
