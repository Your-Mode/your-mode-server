package com.example.yourmode.domain.business.repository;

import com.example.yourmode.domain.business.entity.Business;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, Long> {
}
