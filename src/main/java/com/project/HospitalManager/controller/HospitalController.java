package com.project.HospitalManager.controller;

import com.project.HospitalManager.model.Hospital;
import com.project.HospitalManager.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) {
        return hospitalService.createHospital(hospital);
    }

    @GetMapping("/{id}")
    public Hospital getHospital(@PathVariable int id) {
        return hospitalService.getHospital(id);
    }

    @PutMapping
    public Hospital updateHospital(@RequestBody Hospital hospital) {
        return hospitalService.updateHospital(hospital);
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable int id) {
        hospitalService.deleteHospital(id);
    }
}