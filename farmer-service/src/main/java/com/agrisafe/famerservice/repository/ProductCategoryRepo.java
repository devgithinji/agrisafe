package com.agrisafe.famerservice.repository;

import com.agrisafe.common.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByName(String name);
}
