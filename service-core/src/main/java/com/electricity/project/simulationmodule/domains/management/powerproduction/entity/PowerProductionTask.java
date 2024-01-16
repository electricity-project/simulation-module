package com.electricity.project.simulationmodule.domains.management.powerproduction.entity;

import com.electricity.project.simulationmodule.api.powerstation.PowerStationState;
import com.electricity.project.simulationmodule.configuration.PowerProductionGateway;
import com.electricity.project.simulationmodule.domains.management.powerstation.control.PowerStationService;
import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;

@Slf4j
public abstract class PowerProductionTask<T extends PowerStation> implements Runnable {

    protected final T powerStation;
    protected final WeatherEntity weather;
    private final PowerStationService powerStationService;
    private final PowerProductionGateway powerProductionGateway;
    private final ObjectMapper objectMapper;
    private final int fixedRateMs;
    private final ZonedDateTime messageTimestamp;

    protected PowerProductionTask(T powerStation, WeatherEntity weather, PowerProductionTaskUtil util, ZonedDateTime messageTimestamp) {
        this.powerStation = powerStation;
        this.weather = weather;
        this.powerStationService = util.getPowerStationService();
        this.powerProductionGateway = util.getPowerProductionGateway();
        this.objectMapper = util.getObjectMapper();
        this.fixedRateMs = util.getFixedRateMs();
        this.messageTimestamp = messageTimestamp;
    }

    protected abstract double calculatePowerProduction();

    protected abstract boolean getRandomEvent();

    protected double convertFromWsToMWh(double powerProductionInWatsPerSecond) {
        return powerProductionInWatsPerSecond * 60 * 60 / 1000000;
    }

    @Override
    public void run() {
        simulateRandomEvents();
        double powerProduction = getPowerProduction();
        logPowerProductionState(powerProduction);
        if (powerStation.isConnected()) {
            PowerProductionMessage powerProductionMessage = createPowerProductionMessage(powerProduction);
            sendMessageToBroker(powerProductionMessage);
        }
    }

    private void simulateRandomEvents() {
        for (int i = 0; i < fixedRateMs / 1000 && powerStation.getState() == PowerStationState.WORKING; i++) {
            if (getRandomEvent()) {
                powerStation.setState(PowerStationState.DAMAGED);
                powerStationService.save(powerStation);
                break;
            }
        }
    }

    private double getPowerProduction() {
        return powerStation.getState() == PowerStationState.WORKING ? calculatePowerProduction() : 0;
    }

    private PowerProductionMessage createPowerProductionMessage(double powerProduction) {
        return PowerProductionMessage.builder()
                .ipv6Address(powerStation.getIpv6Address())
                .state(powerStation.getState())
                .power(powerProduction)
                .timestamp(messageTimestamp)
                .build();
    }

    private void logPowerProductionState(double powerProduction) {
        switch (powerStation.getState()) {
            case WORKING ->
                    log.info("Power station: {}, state: {}, produced: {} MWh", powerStation.getIpv6Address(), powerStation.getState(), powerProduction);
            case DAMAGED, STOPPED, MAINTENANCE ->
                    log.info("Power station: {}, not working, state: {}", powerStation.getIpv6Address(), powerStation.getState());
        }
    }

    private void sendMessageToBroker(PowerProductionMessage powerProductionMessage) {
        try {
            powerProductionGateway.sendToMqtt(objectMapper.writeValueAsString(powerProductionMessage));
        } catch (JsonProcessingException e) {
            log.error("Error occurred while parsing power production message: {} to string", powerProductionMessage.toString(), e);
        }
    }
}
