package com.system.saferide.controller;

import com.system.saferide.model.DeviceInformation;
import com.system.saferide.repository.DeviceInformationRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PhoneController {

    private final DeviceInformationRepo repository;

    public PhoneController(DeviceInformationRepo repository) {
        this.repository = repository;
    }

    // Serve the phone page
    @GetMapping("/phone")
    public String phone(Model model) {
        // Add an empty DeviceInformation object for form binding
        model.addAttribute("deviceInformation", new DeviceInformation());
        return "phone"; // Thymeleaf template name: phone.html
    }

    // Handle form submission
    @PostMapping("/send-location")
    public String sendLocation(@ModelAttribute DeviceInformation deviceInformation) {
        // Save the location to the database
        repository.save(deviceInformation);

        // Redirect back to the same page (or anywhere you want)
        return "dashboard";
    }
}
