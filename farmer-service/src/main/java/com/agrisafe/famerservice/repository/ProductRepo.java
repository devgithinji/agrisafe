package com.agrisafe.famerservice.repository;

import com.agrisafe.common.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
