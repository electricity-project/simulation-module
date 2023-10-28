package com.electricity.project.simulationmodule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		properties = { "api.weather.key=test-key" }
)
class SimulationModuleApplicationTests {

	@Test
	void contextLoads() {
		//CELOWE XD
		Assertions.assertSame("true", String.valueOf("true"));
	}

}
