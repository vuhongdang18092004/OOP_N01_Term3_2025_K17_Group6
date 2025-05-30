package com.project.HospitalManager.service;

import com.project.HospitalManager.model.Hospital;
import com.project.HospitalManager.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public Hospital createHospital(Hospital hospital) {
        try {
            return hospitalRepository.save(hospital);
        } catch (Exception e) {
            throw new RuntimeException("Error creating hospital: " + e.getMessage());
        }
    }

    public Hospital getHospital(int id) {
        try {
            return hospitalRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Hospital not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving hospital: " + e.getMessage());
        }
    }

    public List<Hospital> getAllHospitals() {
        try {
            return hospitalRepository.findAll();
        }
        catch (Exception e) {
            throw new RuntimeException("Error retrieving all hospitals: " + e.getMessage());
        }
    }

    public Hospital updateHospital(Hospital hospital) {
        try {
            return hospitalRepository.save(hospital);
        } catch (Exception e) {
            throw new RuntimeException("Error updating hospital: " + e.getMessage());
        }
    }

    public void deleteHospital(int id) {
        try {
            hospitalRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting hospital: " + e.getMessage());
        }
    }
}