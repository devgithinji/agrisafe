package com.agrisafe.common.model.farmer;

import com.agrisafe.common.model.BaseEntity;
import com.agrisafe.common.model.Product;
import com.agrisafe.common.model.farmer.Farm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(name = "seasons")
public class Season extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "farm_id")
    private Farm farm;

    @Column(name = "planting_date", nullable = false)
    private LocalDate plantingDate;

    @Column(name = "season_duration", nullable = false)
    private int seasonDuration;

    private String notes;

    private int yield;
}
