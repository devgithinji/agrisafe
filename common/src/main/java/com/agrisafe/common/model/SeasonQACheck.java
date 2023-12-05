package com.agrisafe.common.model;

import com.agrisafe.common.model.farmer.CultivationPractice;
import com.agrisafe.common.model.qa.QAOrganisation;
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
@Table(name = "season_qa_checks")
public class SeasonQACheck extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "qa_organisation_id")
    public QAOrganisation qaOrganisation;
    @OneToOne
    @JoinColumn(name = "culitivation_practice_id")
    private CultivationPractice cultivationPractice;
    @OneToOne
    @JoinColumn(name = "product_quality_id")
    private ProductQuality productQuality;
    @Column(name = "haresting_done")
    private boolean harvestingDone;
    private int yield;
}
