package com.project.HospitalManager.service;

import com.project.HospitalManager.model.Trip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TripServiceTest {

    @Autowired
    private TripService tripService;

    @Test
    public void testTripCRUD() {
        try {
            // Create
            Trip trip = new Trip();
            trip.setAmbulanceId(1);
            trip.setDriverName("Jane Doe");
            trip.setStatus("SCHEDULED");
            Trip created = tripService.createTrip(trip);
            assertNotNull(created.getId());

            // Read
            Trip read = tripService.getTrip(created.getId());
            assertEquals("Jane Doe", read.getDriverName());

            // Update
            read.setStatus("COMPLETED");
            Trip updated = tripService.updateTrip(read);
            assertEquals("COMPLETED", updated.getStatus());

            // Delete
            tripService.deleteTrip(created.getId());
            assertThrows(RuntimeException.class, () -> tripService.getTrip(created.getId()));
        } catch (Exception e) {
            fail("Test failed: " + e.getMessage());
        }
    }
}