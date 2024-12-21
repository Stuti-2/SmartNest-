package com.springHome.SmartNest.Service;

import com.springHome.SmartNest.Entity.Device;
import com.springHome.SmartNest.Entity.User;
import com.springHome.SmartNest.Entity.UserPrinciple;
import com.springHome.SmartNest.Repository.DeviceRepository;
import com.springHome.SmartNest.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeviceRepository deviceRepository;
//    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user =   userRepository.findByUsername(username);
      if (user == null){
          System.out.println("User 404");
          throw new UsernameNotFoundException("User 404");
      }
        return new UserPrinciple(user);
    }

    public User addDevicesToUser(Long userId, List<Long> deviceIds) {
        // Fetch the user by their ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the devices by their IDs
        List<Device> devices = deviceRepository.findAllById(deviceIds);

        // Add devices to the user's device list
        user.getDevices().addAll(devices);

        // Save the updated user
        return userRepository.save(user);
    }

    // Get all users and their devices
    public List<String> getUsersAndDevices() {
        List<User> users = userRepository.findAll();

        // Collect user-device info
        return users.stream()
                .map(user -> "User: " + user.getUsername() + " owns devices: " +
                        user.getDevices().stream()
                                .map(Device::getName)  // Get device names
                                .reduce((d1, d2) -> d1 + ", " + d2).orElse("No devices"))
                .toList();
    }

    // Create a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Get a list of all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Update an existing user
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword()); // Ideally, hash the password before saving
        return userRepository.save(user);
    }

    // Delete a user by ID
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
