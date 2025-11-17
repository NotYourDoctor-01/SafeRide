package com.system.saferide.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DeviceInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId; // unique ID per device
    private double longitude;
    private double latitude;
    private double pulse;
    private double oxygenLevel;
    private boolean rescued;

    // Constructors
    public DeviceInformation() {
    }

    public DeviceInformation(String deviceId) {
        this.deviceId = deviceId;
        this.rescued = false; // default
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getPulse() {
        return pulse;
    }

    public void setPulse(double pulse) {
        this.pulse = pulse;
    }

    public double getOxygenLevel() {
        return oxygenLevel;
    }

    public void setOxygenLevel(double oxygenLevel) {
        this.oxygenLevel = oxygenLevel;
    }

    public boolean getRescued() {
        return rescued;
    }

    public void setRescued(boolean rescued) {
        this.rescued = rescued;
    }
}
