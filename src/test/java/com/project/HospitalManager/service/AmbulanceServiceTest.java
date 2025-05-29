package com.project.HospitalManager.service;

import com.project.HospitalManager.model.Ambulance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AmbulanceServiceTest {

    @Autowired
    private AmbulanceService ambulanceService;

    @Test
    public void testAmbulanceCRUD() {
        try {
            // Create
            Ambulance ambulance = new Ambulance();
            ambulance.setLicensePlate("ABC-456");
            ambulance.setType("Van");
            ambulance.setStatus("AVAILABLE");
            Ambulance created = ambulanceService.createAmbulance(ambulance);
            assertNotNull(created.getId());

            // Read
            Ambulance read = ambulanceService.getAmbulance(created.getId());
            assertEquals("ABC-456", read.getLicensePlate());

            // Update
            read.setStatus("IN_USE");
            Ambulance updated = ambulanceService.updateAmbulance(read);
            assertEquals("IN_USE", updated.getStatus());

            // Delete
            ambulanceService.deleteAmbulance(created.getId());
            assertThrows(RuntimeException.class, () -> ambulanceService.getAmbulance(created.getId()));
        } catch (Exception e) {
            fail("Test failed: " + e.getMessage());
        }
    }
}