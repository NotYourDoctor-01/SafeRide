package com.system.saferide.repository;

import com.system.saferide.model.DeviceInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceInformationRepo extends JpaRepository<DeviceInformation, Long> {
    List<DeviceInformation> findByRescuedFalse();

    Optional<DeviceInformation> findByDeviceId(String deviceId);
}
