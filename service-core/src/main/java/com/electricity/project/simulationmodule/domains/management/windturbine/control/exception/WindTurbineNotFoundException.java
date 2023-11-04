package com.electricity.project.simulationmodule.domains.management.windturbine.control.exception;

import com.electricity.project.simulationmodule.domains.management.powerstation.control.exception.PowerStationNotExistsException;

public class WindTurbineNotFoundException extends PowerStationNotExistsException {
    public WindTurbineNotFoundException(long id) {
        super("Wind turbine with id = %d not found".formatted(id));
    }
}
