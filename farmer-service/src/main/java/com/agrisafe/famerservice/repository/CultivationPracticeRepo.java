package com.agrisafe.famerservice.repository;

import com.agrisafe.common.model.farmer.CultivationPractice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CultivationPracticeRepo extends JpaRepository<CultivationPractice, Long> {
}
