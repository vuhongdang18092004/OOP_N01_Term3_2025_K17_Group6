package com.project.HospitalManager.service;

import com.project.HospitalManager.model.Ambulance;
import com.project.HospitalManager.repository.AmbulanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmbulanceService {

    @Autowired
    private AmbulanceRepository ambulanceRepository;

    public Ambulance createAmbulance(Ambulance ambulance) {
        try {
            return ambulanceRepository.save(ambulance);
        } catch (Exception e) {
            throw new RuntimeException("Error creating ambulance: " + e.getMessage());
        }
    }

    public Ambulance getAmbulance(int id) {
        try {
            return ambulanceRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ambulance not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving ambulance: " + e.getMessage());
        }
    }

    public List<Ambulance> getAllAmbulances() {
        try {
            return ambulanceRepository.findAll();
        }
        catch (Exception e) {
            throw new RuntimeException("Error retrieving ambulances: " + e.getMessage());
        }
    }

    public Ambulance updateAmbulance(Ambulance ambulance) {
        try {
            return ambulanceRepository.save(ambulance);
        } catch (Exception e) {
            throw new RuntimeException("Error updating ambulance: " + e.getMessage());
        }
    }

    public void deleteAmbulance(int id) {
        try {
            ambulanceRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting ambulance: " + e.getMessage());
        }
    }

    public List<Ambulance> getAvailableAmbulances() {
        try {
            return ambulanceRepository.findByStatus("AVAILABLE");
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving available ambulances: " + e.getMessage());
        }
    }
}