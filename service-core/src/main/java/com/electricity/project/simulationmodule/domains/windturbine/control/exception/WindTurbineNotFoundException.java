package com.electricity.project.simulationmodule.domains.windturbine.control.exception;

public class WindTurbineNotFoundException extends RuntimeException {
    public WindTurbineNotFoundException(long id) {
        super("Wind turbine with id = %d not found".formatted(id));
    }
}
