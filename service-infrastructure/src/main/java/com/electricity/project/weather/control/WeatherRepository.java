package com.electricity.project.weather.control;

import com.electricity.project.weather.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
    @NonNull
    WeatherEntity getFirstByOrderByTimestampDesc();
}
