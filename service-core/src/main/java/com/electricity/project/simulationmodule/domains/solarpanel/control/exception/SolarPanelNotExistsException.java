package com.electricity.project.simulationmodule.domains.solarpanel.control.exception;

import com.electricity.project.simulationmodule.domains.power.control.exception.PowerStationNotExistsException;

public class SolarPanelNotExistsException extends PowerStationNotExistsException {
    public SolarPanelNotExistsException(long id) {
        super("Solar Panel with id = %d not exists".formatted(id));
    }
}
