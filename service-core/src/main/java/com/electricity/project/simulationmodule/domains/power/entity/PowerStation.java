package com.electricity.project.simulationmodule.domains.power.entity;

import com.electricity.project.simulationmodule.api.PowerStationState;
import com.electricity.project.simulationmodule.domains.powerproduction.entity.PowerProductionTask;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PowerStation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Column(length = 15)
    private String ipv4Address;

    @Enumerated(EnumType.STRING)
    private PowerStationState state;

    private LocalDateTime createdTime;

    public abstract PowerProductionTask<? extends PowerStation> createTask();

}
