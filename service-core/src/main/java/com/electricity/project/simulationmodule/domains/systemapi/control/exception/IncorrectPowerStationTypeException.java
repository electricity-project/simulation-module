package com.electricity.project.simulationmodule.domains.systemapi.control.exception;

public class IncorrectPowerStationTypeException extends RuntimeException {
    public IncorrectPowerStationTypeException(Object value) {
        super("Incorrect type for object: %s".formatted(value));
    }
}
