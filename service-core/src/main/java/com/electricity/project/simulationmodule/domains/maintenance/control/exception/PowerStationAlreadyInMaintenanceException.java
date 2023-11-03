package com.electricity.project.simulationmodule.domains.maintenance.control.exception;

public class PowerStationAlreadyInMaintenanceException extends RuntimeException {
    public PowerStationAlreadyInMaintenanceException(long id) {
        super("Power station with id = %d is already in maintenance mode".formatted(id));
    }
}
