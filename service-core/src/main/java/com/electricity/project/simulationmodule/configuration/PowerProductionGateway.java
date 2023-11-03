package com.electricity.project.simulationmodule.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.MessagingGateway;

@Profile("default")
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface PowerProductionGateway {
    void sendToMqtt(String powerProductionMessage);
}
