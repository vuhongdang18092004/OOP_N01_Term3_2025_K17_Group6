package com.project.HospitalManager.controller;

import com.project.HospitalManager.model.Trip;
import com.project.HospitalManager.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripService.createTrip(trip);
    }

    @GetMapping("/{id}")
    public Trip getTrip(@PathVariable int id) {
        return tripService.getTrip(id);
    }

    @PutMapping
    public Trip updateTrip(@RequestBody Trip trip) {
        return tripService.updateTrip(trip);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable int id) {
        tripService.deleteTrip(id);
    }
}