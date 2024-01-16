package com.electricity.project.simulationmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.electricity.project")
public class SimulationModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulationModuleApplication.class, args);
	}

}
