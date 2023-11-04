package com.electricity.project.simulationmodule.domains.management.powerstation.control.exception;

public class PowerStationNotExistsException extends RuntimeException {
    public PowerStationNotExistsException(String message) {
        super("Power station not exists: " + message);
    }

    public PowerStationNotExistsException(long id) {
        super("Power station with id = %d not exists".formatted(id));
    }

    public static PowerStationNotExistsException fromIpv4Address(String ipv4Address) {
        return new PowerStationNotExistsException("Power station with ipv4Address = %s not exists".formatted(ipv4Address));
    }
}
