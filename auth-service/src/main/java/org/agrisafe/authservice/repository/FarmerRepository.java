package org.agrisafe.authservice.repository;

import com.agrisafe.common.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

}
