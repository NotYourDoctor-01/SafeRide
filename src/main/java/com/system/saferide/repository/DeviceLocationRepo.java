package com.system.saferide.repository;

import com.system.saferide.model.DeviceLocation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceLocationRepo extends JpaRepository<DeviceLocation, Long> {
    DeviceLocation findTopByOrderByIdDesc();
}
