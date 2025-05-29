package com.project.HospitalManager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int ambulanceId;
    private String driverName;
    private String doctorName;
    private String patientName;
    private String pickupLocation;
    private String hospitalDestination;
    private String status; // e.g., SCHEDULED, IN_PROGRESS, COMPLETED
}
