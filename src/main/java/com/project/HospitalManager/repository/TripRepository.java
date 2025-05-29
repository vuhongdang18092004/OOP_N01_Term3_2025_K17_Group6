package com.project.HospitalManager.repository;

import com.project.HospitalManager.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}