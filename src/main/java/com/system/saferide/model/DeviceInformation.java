package com.system.saferide.model;

import jakarta.persistence.*;

@Entity
@Table(name = "device_information")
public class DeviceInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String deviceId;

    private Double latitude;
    private Double longitude;

    private Integer heartRate;
    private Integer spo2;

    private boolean impactDetected;
    private boolean rescued;

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Integer getHeartRate() { return heartRate; }
    public void setHeartRate(Integer heartRate) { this.heartRate = heartRate; }

    public Integer getSpo2() { return spo2; }
    public void setSpo2(Integer spo2) { this.spo2 = spo2; }

    public boolean isImpactDetected() { return impactDetected; }
    public void setImpactDetected(boolean impactDetected) { this.impactDetected = impactDetected; }

    public boolean isRescued() { return rescued; }
    public void setRescued(boolean rescued) { this.rescued = rescued; }
}
