package com.electricity.project.simulationmodule.domains.windturbine.control;

import com.electricity.project.simulationmodule.domains.windturbine.entity.WindTurbine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WindTurbineRepository extends JpaRepository<WindTurbine, Long> { }
