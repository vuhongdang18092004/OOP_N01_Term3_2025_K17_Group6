package com.project.HospitalManager.controller;

import com.project.HospitalManager.model.Trip;
import com.project.HospitalManager.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping("/list")
    public String listTrips(Model model) {
        try {
            List<Trip> trips = tripService.getAllTrips();
            model.addAttribute("trips", trips);
            return "trips";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi tải danh sách chuyến đi: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        try {
            Trip createdTrip = tripService.createTrip(trip);
            return ResponseEntity.ok(createdTrip);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        try {
            List<Trip> trips = tripService.getAllTrips();
            return ResponseEntity.ok(trips);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable int id) {
        try {
            Trip trip = tripService.getTrip(id);
            return ResponseEntity.ok(trip);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Trip> updateTrip(@RequestBody Trip trip) {
        try {
            Trip updatedTrip = tripService.updateTrip(trip);
            return ResponseEntity.ok(updatedTrip);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable int id) {
        try {
            tripService.deleteTrip(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}