package com.project.HospitalManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ambulance {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String licensePlate;
    private String type;
    private String imageUrl;
    private int capacity;
    private boolean hasDefibrillator;
    private int bedCount;
    private int syringeCount;
    private String medicalEquipment;
    private String status; // e.g., AVAILABLE, IN_USE, MAINTENANCE
}
