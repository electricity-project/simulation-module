package com.electricity.project.simulationmodule.domains.management.solarpanel.control;

import com.electricity.project.simulationmodule.domains.management.solarpanel.entity.SolarPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolarPanelRepository extends JpaRepository<SolarPanel, Long> { }
