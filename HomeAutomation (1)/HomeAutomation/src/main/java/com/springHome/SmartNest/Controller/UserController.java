package com.springHome.SmartNest.Controller;
import com.springHome.SmartNest.Entity.User;
import com.springHome.SmartNest.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user/{userId}/devices")
    public ResponseEntity<User> addDevicesToUser(@PathVariable Long userId, @RequestBody List<Long> deviceIds) {
        User updatedUser = userService.addDevicesToUser(userId, deviceIds);
        return ResponseEntity.ok(updatedUser);
    }

    // Endpoint to get all users and their devices
    @GetMapping("/devices")
    public ResponseEntity<List<String>> getUsersAndDevices() {
        List<String> userDeviceDetails = userService.getUsersAndDevices();
        return ResponseEntity.ok(userDeviceDetails);
    }


    @PostMapping("/postuser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/allusers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user-{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

