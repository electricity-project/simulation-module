package com.electricity.project.simulationmodule.mqttconsumer;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SimulationMqttConsumerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SimulationMqttConsumerApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
