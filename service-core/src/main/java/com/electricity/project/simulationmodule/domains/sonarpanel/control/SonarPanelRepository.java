package com.electricity.project.simulationmodule.domains.sonarpanel.control;

import com.electricity.project.simulationmodule.domains.sonarpanel.entity.SonarPanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SonarPanelRepository extends JpaRepository<SonarPanel, Long> { }
