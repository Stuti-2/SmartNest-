package com.springHome.SmartNest.Service;

import com.springHome.SmartNest.Entity.Device;
import com.springHome.SmartNest.Repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

//     Create a new device
    public Device createDevice(Device device) {
        return deviceRepository.save(device);
    }


    // Get a list of all devices
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    // Get a device by ID
    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found"));
    }

    // Update an existing device
    public Device updateDevice(Long id, Device deviceDetails) {
        Device device = getDeviceById(id);  // Fetch the device by ID
        device.setName(deviceDetails.getName());  // Assuming your `Device` entity has a `name` field
        device.setType(deviceDetails.getType());  // Update other relevant fields
        return deviceRepository.save(device);  // Save the updated device
    }

    // Delete a device by ID
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }











}
