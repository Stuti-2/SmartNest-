package com.springHome.SmartNest.Repository;
import com.springHome.SmartNest.Entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
