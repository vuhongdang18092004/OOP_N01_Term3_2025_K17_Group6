package com.project.HospitalManager.service;

import com.project.HospitalManager.model.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class HospitalServiceTest {

    @Autowired
    private HospitalService hospitalService;

    @Test
    public void testHospitalCRUD() {
        try {
            // Create
            Hospital hospital = new Hospital();
            hospital.setName("City Hospital");
            hospital.setAddress("789 Pine St");
            Hospital created = hospitalService.createHospital(hospital);
            assertNotNull(created.getId());

            // Read
            Hospital read = hospitalService.getHospital(created.getId());
            assertEquals("City Hospital", read.getName());

            // Update
            read.setAddress("101 Elm St");
            Hospital updated = hospitalService.updateHospital(read);
            assertEquals("101 Elm St", updated.getAddress());

            // Delete
            hospitalService.deleteHospital(created.getId());
            assertThrows(RuntimeException.class, () -> hospitalService.getHospital(created.getId()));
        } catch (Exception e) {
            fail("Test failed: " + e.getMessage());
        }
    }
}