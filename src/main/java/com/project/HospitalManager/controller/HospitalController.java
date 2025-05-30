package com.project.HospitalManager.controller;

import com.project.HospitalManager.model.Hospital;
import com.project.HospitalManager.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/list")
    public String listHospitals(Model model) {
        try {
            List<Hospital> hospitals = hospitalService.getAllHospitals();
            model.addAttribute("hospitals", hospitals);
            return "hospitals";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách bệnh viện: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        try {
            Hospital createdHospital = hospitalService.createHospital(hospital);
            return ResponseEntity.ok(createdHospital);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        try {
            List<Hospital> hospitals = hospitalService.getAllHospitals();
            return ResponseEntity.ok(hospitals);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospital(@PathVariable int id) {
        try {
            Hospital hospital = hospitalService.getHospital(id);
            return ResponseEntity.ok(hospital);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Hospital> updateHospital(@RequestBody Hospital hospital) {
        try {
            Hospital updatedHospital = hospitalService.updateHospital(hospital);
            return ResponseEntity.ok(updatedHospital);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospital(@PathVariable int id) {
        try {
            hospitalService.deleteHospital(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}