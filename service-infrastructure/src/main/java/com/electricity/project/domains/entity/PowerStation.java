package com.electricity.project.domains.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class PowerStation {
    protected Long id;
    protected String name;
    protected String ipv4Address;
    protected PowerStationState state;
    protected LocalDateTime createdTime;
}
