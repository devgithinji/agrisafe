package com.agrisafe.common.model.farmer;

import com.agrisafe.common.model.BaseEntity;
import com.agrisafe.common.model.auth.Farmer;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "farms")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Farm extends BaseEntity {
    private String name;
    private String location;
    private String size;
    @ManyToOne
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

}
