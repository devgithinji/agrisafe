package com.agrisafe.common.model.qa;

import com.agrisafe.common.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "qa_organisations")
@NoArgsConstructor
public class QAOrganisation extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String logo;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String password;
}
