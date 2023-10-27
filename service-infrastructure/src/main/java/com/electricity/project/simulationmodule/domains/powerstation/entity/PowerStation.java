package com.electricity.project.simulationmodule.domains.powerstation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PowerStation {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(length = 15)
    private String ipv4Address;

    @Enumerated(EnumType.STRING)
    private PowerStationState state;

    private LocalDateTime createdTime;

}
