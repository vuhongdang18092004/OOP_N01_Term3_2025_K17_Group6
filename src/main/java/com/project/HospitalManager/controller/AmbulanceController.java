package com.project.HospitalManager.controller;

import com.project.HospitalManager.model.Ambulance;
import com.project.HospitalManager.service.AmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/ambulances")
public class AmbulanceController {

    @Autowired
    private AmbulanceService ambulanceService;

    @PostMapping
    public Ambulance createAmbulance(@RequestBody Ambulance ambulance) {
        return ambulanceService.createAmbulance(ambulance);
    }

    @GetMapping("/{id}")
    public Ambulance getAmbulance(@PathVariable int id) {
        return ambulanceService.getAmbulance(id);
    }

    @PutMapping
    public Ambulance updateAmbulance(@RequestBody Ambulance ambulance) {
        return ambulanceService.updateAmbulance(ambulance);
    }

    @DeleteMapping("/{id}")
    public void deleteAmbulance(@PathVariable int id) {
        ambulanceService.deleteAmbulance(id);
    }

    @GetMapping("/available")
    public List<Ambulance> getAvailableAmbulances() {
        return ambulanceService.getAvailableAmbulances();
    }
}