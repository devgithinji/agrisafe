package com.agrisafe.authservice.repository;

import com.agrisafe.common.model.auth.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {
    Optional<Farmer> findFarmerByEmailOrIdNOOrPhoneNumber(String email, String idNo, String phoneNumber);

    Optional<Farmer> findFarmerByEmail(String email);
}
