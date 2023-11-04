package com.electricity.project.simulationmodule.domains.management.powerstation.control;

import com.electricity.project.simulationmodule.domains.management.powerstation.entity.PowerStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PowerStationRepository extends JpaRepository<PowerStation, Long> {
    Optional<PowerStation> findFirstByIpv4Address(String ipv4Address);
}
