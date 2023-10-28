package com.electricity.project.simulationmodule.domains.powerproduction.exception;

public class WeatherNotFoundException extends RuntimeException {
    public WeatherNotFoundException() {
        super("No weather data");
    }
}
