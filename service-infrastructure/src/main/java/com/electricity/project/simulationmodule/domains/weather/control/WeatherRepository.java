package com.electricity.project.simulationmodule.domains.weather.control;

import com.electricity.project.simulationmodule.domains.weather.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    @NonNull
    WeatherEntity getFirstByOrderByTimestampDesc();
}
