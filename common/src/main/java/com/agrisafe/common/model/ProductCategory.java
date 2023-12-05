package com.agrisafe.common.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "product_categories")
@NoArgsConstructor
public class ProductCategory extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;
}
