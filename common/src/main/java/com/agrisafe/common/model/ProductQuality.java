package com.agrisafe.common.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "productqualities")
public class ProductQuality extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "pesticide_residue_levels", nullable = false)
    private QualityLevel pesticideResidueLevels;
    @Enumerated(EnumType.STRING)
    @Column(name = "moisture_levels", nullable = false)
    private QualityLevel moistureLevel;
    @Enumerated(EnumType.STRING)
    @Column(name = "microbial_contamination", nullable = false)
    private QualityLevel microbialContamination;
    @Enumerated(EnumType.STRING)
    @Column(name = "defects", nullable = false)
    private QualityLevel defects;
    @Enumerated(EnumType.STRING)
    @Column(name = "ph_levels", nullable = false)
    private QualityLevel phLevels;
}
