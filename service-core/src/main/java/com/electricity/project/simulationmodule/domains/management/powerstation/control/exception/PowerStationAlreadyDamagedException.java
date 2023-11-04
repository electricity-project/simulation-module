package com.electricity.project.simulationmodule.domains.management.powerstation.control.exception;

public class PowerStationAlreadyDamagedException extends RuntimeException {
    public PowerStationAlreadyDamagedException(long id) {
        super("Power station with id = %d is already damaged".formatted(id));
    }
}
