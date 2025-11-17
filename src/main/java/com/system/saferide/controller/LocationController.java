package com.system.saferide.controller;

import com.system.saferide.model.DeviceInformation;
import com.system.saferide.repository.DeviceInformationRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    private final DeviceInformationRepo repository;

    public LocationController(DeviceInformationRepo repository) {
        this.repository = repository;
    }

    // Return all devices that are not rescued
    @GetMapping("/location")
    public List<DeviceInformation> getActiveDevices() {
        return repository.findByRescuedFalse();
    }

    // Receive location updates from a device
    @PostMapping("/send-location")
    public DeviceInformation updateLocation(@RequestBody DeviceInformation incoming) {
        // If the device already exists, update it; otherwise, save new
        DeviceInformation device = repository.findById(incoming.getId())
                .orElse(incoming); // If new, just use incoming object

        device.setLatitude(incoming.getLatitude());
        device.setLongitude(incoming.getLongitude());
        device.setPulse(incoming.getPulse());
        device.setOxygenLevel(incoming.getOxygenLevel());
        device.setRescued(incoming.getRescued());

        return repository.save(device);
    }
}
