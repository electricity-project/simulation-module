package com.electricity.project.simulationmodule.domains.systemapi.control.exception;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationState;

public class IncorrectStateForOperationException extends RuntimeException {
    public IncorrectStateForOperationException(PowerStationState state, String operation) {
        super("Operation %s cannot be performed when in state %s".formatted(operation, state));
    }
}
