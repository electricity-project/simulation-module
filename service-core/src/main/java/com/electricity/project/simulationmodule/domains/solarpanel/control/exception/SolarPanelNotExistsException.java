package com.electricity.project.simulationmodule.domains.solarpanel.control.exception;

public class SolarPanelNotExistsException extends RuntimeException {
    public SolarPanelNotExistsException(long id) {
        super("Solar Panel with id = %d not exists".formatted(id));
    }
}
