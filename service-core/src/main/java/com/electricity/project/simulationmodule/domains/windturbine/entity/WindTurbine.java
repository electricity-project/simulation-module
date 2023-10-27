package com.electricity.project.simulationmodule.domains.windturbine.entity;

import com.electricity.project.simulationmodule.domains.powerstation.entity.PowerStation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WindTurbine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long bladeLength;

    private Long minimalEffectivityCoefficient;

    private Long maximalEffectivityCoefficient;

    private Long powerCoefficient;

    @OneToOne
    private PowerStation powerStation;
}
