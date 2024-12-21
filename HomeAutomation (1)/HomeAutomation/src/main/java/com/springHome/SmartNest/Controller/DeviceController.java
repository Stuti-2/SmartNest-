package com.springHome.SmartNest.Controller;

import com.springHome.SmartNest.Entity.Device;
import com.springHome.SmartNest.Service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    // Create a new device
    @PostMapping("/postdevice")
    public ResponseEntity<Device> createDevice(@RequestBody Device device) {
        Device newDevice = deviceService.createDevice(device);
        return ResponseEntity.ok(newDevice);
    }

    // Get all devices
    @GetMapping("/alldevices")
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    // Get a device by ID
    @GetMapping("/getdevice{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    // Update a device
    @PutMapping("/updatedevice{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device deviceDetails) {
        Device updatedDevice = deviceService.updateDevice(id, deviceDetails);
        return ResponseEntity.ok(updatedDevice);
    }

    // Delete a device
    @DeleteMapping("/deletedevice{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}

