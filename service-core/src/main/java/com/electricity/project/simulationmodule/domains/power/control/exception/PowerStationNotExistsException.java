package com.electricity.project.simulationmodule.domains.power.control.exception;

public class PowerStationNotExistsException extends RuntimeException {
    public PowerStationNotExistsException(String message) {
        super("Power station not exists: " + message);
    }

    public PowerStationNotExistsException(long id) {
        super("Power station with id = %d not exists".formatted(id));
    }
}
