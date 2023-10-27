package com.electricity.project.simulationmodule.domains.weather.data;

import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public abstract class WeatherResponseAbstract {

    protected LocalDateTime time;

    protected double celsiusTemperature;

    protected double kphWindSpeed;

    protected double mbPressure;

    protected int percentageHumidity;

    protected int percentageCloudy;

    public abstract LocalDateTime getTime();

    public abstract double getCelsiusTemperature();

    public abstract double getKphWindSpeed();

    public abstract double getMbPressure();

    public abstract int getPercentageHumidity();

    public abstract int getPercentageCloudy();
}
