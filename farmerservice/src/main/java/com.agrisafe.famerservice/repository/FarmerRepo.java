package com.agrisafe.famerservice.repository;

import com.agrisafe.common.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepo extends JpaRepository<Farmer, Long> {
}
