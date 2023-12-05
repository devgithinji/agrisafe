package com.agrisafe.common.model.farmer;

import com.agrisafe.common.model.BaseEntity;
import com.agrisafe.common.model.QualityLevel;
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
@Table(name = "cultivation_practices")
public class CultivationPractice extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "chemical_ferilizer_usage", nullable = false)
    private QualityLevel chemicalFertilizerUsage;
    @Enumerated(EnumType.STRING)
    @Column(name = "heavy_metal_contamination", nullable = false)
    private QualityLevel heavyMetalContamination;
    @Enumerated(EnumType.STRING)
    @Column(name = "gmo", nullable = false)
    private QualityLevel gmo;
    @Enumerated(EnumType.STRING)
    @Column(name = "antibiotic_usage", nullable = false)
    private QualityLevel antibioticUsage;
    @Enumerated(EnumType.STRING)
    @Column(name = "water_quality", nullable = false)
    private QualityLevel waterQuality;
    @Enumerated(EnumType.STRING)
    @Column(name = "over_sparying", nullable = false)
    private QualityLevel overSpraying;
}
