package com.electricity.project.weather.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    @Column(name = "TIMESTAMP")
    private LocalDateTime timestamp;

    @Column(name = "TEMPERATURE_C")
    private double temperature;

    @Column(name = "WIND_SPEED_MPH")
    private double windSpeed;

    @Column(name = "HUMIDITY")
    private double humidity;

    @Column(name = "CLOUD")
    private double cloud;

    @Column(name = "PRESSURE_PA")
    private double pressure;
}
