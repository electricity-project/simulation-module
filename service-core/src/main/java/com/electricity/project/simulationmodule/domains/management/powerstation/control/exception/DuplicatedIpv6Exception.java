package com.electricity.project.simulationmodule.domains.management.powerstation.control.exception;

public class DuplicatedIpv6Exception extends RuntimeException {
    public DuplicatedIpv6Exception(String ipv6Address) {
        super("Power station with ipv6 = %s already exists".formatted(ipv6Address));
    }
}
