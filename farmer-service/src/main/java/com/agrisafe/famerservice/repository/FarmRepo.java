package com.agrisafe.famerservice.repository;

import com.agrisafe.common.model.farmer.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepo extends JpaRepository<Farm, Long> {
}
