package com.project.HospitalManager.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String contactNumber;
}
