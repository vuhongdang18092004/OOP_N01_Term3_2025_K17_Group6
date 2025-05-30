package com.project.HospitalManager.controller;

import com.project.HospitalManager.model.Ambulance;
import com.project.HospitalManager.service.AmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ambulances")
public class AmbulanceController {

    @Autowired
    private AmbulanceService ambulanceService;

    @GetMapping("/list")
    public String listAmbulances(Model model) {
        try {
            List<Ambulance> ambulances = ambulanceService.getAllAmbulances();
            model.addAttribute("ambulances", ambulances);
            return "ambulances";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách xe cứu thương: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping
    public ResponseEntity<Ambulance> createAmbulance(@RequestBody Ambulance ambulance) {
        try {
            Ambulance createdAmbulance = ambulanceService.createAmbulance(ambulance);
            return ResponseEntity.ok(createdAmbulance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ambulance> getAmbulance(@PathVariable int id) {
        try {
            Ambulance ambulance = ambulanceService.getAmbulance(id);
            return ResponseEntity.ok(ambulance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Ambulance> updateAmbulance(@RequestBody Ambulance ambulance) {
        try {
            Ambulance updatedAmbulance = ambulanceService.updateAmbulance(ambulance);
            return ResponseEntity.ok(updatedAmbulance);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAmbulance(@PathVariable int id) {
        try {
            ambulanceService.deleteAmbulance(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Ambulance>> getAvailableAmbulances() {
        try {
            List<Ambulance> availableAmbulances = ambulanceService.getAvailableAmbulances();
            return ResponseEntity.ok(availableAmbulances);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}