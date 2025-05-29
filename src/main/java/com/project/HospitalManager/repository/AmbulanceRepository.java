package com.project.HospitalManager.repository;

import com.project.HospitalManager.model.Ambulance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmbulanceRepository extends JpaRepository<Ambulance, Integer> {
    List<Ambulance> findByStatus(String status);
}