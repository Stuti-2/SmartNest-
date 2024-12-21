package com.springHome.SmartNest.Repository;

import com.springHome.SmartNest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query("SELECT u FROM User u JOIN FETCH u.devices WHERE u.id = :userId")
    User findUserWithDevices(@Param("userId") Long userId);

}
