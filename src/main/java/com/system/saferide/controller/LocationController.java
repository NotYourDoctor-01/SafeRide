package com.system.saferide.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private double lastLat = 0;
    private double lastLng = 0;

    @PostMapping("/location")
    public String updateLocation(@RequestParam double lat, @RequestParam double lng) {
        lastLat = lat;
        lastLng = lng;
        System.out.println("Received location: " + lat + ", " + lng);
        return "OK";
    }

    @GetMapping("/location")
    public Map<String, Double> getLocation() {
        return Map.of("lat", lastLat, "lng", lastLng);
    }

    @GetMapping("/location/latest")
    public Map<String, Double> getLatestLocation() {
        return Map.of("latitude", lastLat, "longitude", lastLng);
    }

}
