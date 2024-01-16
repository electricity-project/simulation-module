package com.electricity.project.simulationmodule.domains.management.powerproduction.control.exception;

public class WeatherNotFoundException extends RuntimeException {
    public WeatherNotFoundException() {
        super("No weather data");
    }
}
