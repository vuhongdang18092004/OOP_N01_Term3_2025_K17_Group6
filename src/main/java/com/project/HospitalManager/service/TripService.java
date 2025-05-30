package com.project.HospitalManager.service;

import com.project.HospitalManager.model.Trip;
import com.project.HospitalManager.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip createTrip(Trip trip) {
        try {
            return tripRepository.save(trip);
        } catch (Exception e) {
            throw new RuntimeException("Error creating trip: " + e.getMessage());
        }
    }

    public Trip getTrip(int id) {
        try {
            return tripRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving trip: " + e.getMessage());
        }
    }

    public List<Trip> getAllTrips() {
        try {
            return tripRepository.findAll();
        }
        catch (Exception e) {
            throw new RuntimeException("Error retrieving all trips: " + e.getMessage());
        }
    }

    public Trip updateTrip(Trip trip) {
        try {
            return tripRepository.save(trip);
        } catch (Exception e) {
            throw new RuntimeException("Error updating trip: " + e.getMessage());
        }
    }

    public void deleteTrip(int id) {
        try {
            tripRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting trip: " + e.getMessage());
        }
    }
}