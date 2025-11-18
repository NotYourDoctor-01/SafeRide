package com.system.saferide.controller;

import com.system.saferide.model.DeviceInformation;
import com.system.saferide.repository.DeviceInformationRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    private final DeviceInformationRepo repository;

    public LocationController(DeviceInformationRepo repository) {
        this.repository = repository;
    }

    @GetMapping("/location")
    public List<DeviceInformation> getActiveDevices() {
        return repository.findByRescuedFalse();
    }

    @PostMapping("/send-location")
    public ResponseEntity<DeviceInformation> updateLocation(@RequestBody DeviceInformation incoming) {
        if (incoming.getDeviceId() == null || incoming.getDeviceId().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        DeviceInformation device = repository.findByDeviceId(incoming.getDeviceId())
                .orElseGet(DeviceInformation::new);

        device.setDeviceId(incoming.getDeviceId());
        device.setLatitude(incoming.getLatitude());
        device.setLongitude(incoming.getLongitude());
        device.setHeartRate(incoming.getHeartRate());
        device.setSpo2(incoming.getSpo2());
        device.setImpactDetected(incoming.isImpactDetected());
        // Do not overwrite rescued unless explicitly provided true
        if (incoming.isRescued()) {
            device.setRescued(true);
        }

        DeviceInformation saved = repository.save(device);
        return ResponseEntity.ok(saved);
    }
}
