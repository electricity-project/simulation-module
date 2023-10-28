package com.electricity.project.simulationmodule.domains.sonarpanel.control.exception;

public class SonarPanelNotExistsException extends RuntimeException {
    public SonarPanelNotExistsException(long id) {
        super("Sonar Panel with id = %d not exists".formatted(id));
    }
}
