package com.project.HospitalManager.repository;

import com.project.HospitalManager.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}