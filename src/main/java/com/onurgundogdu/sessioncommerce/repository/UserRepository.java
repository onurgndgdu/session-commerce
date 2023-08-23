package com.onurgundogdu.sessioncommerce.repository;

import com.onurgundogdu.sessioncommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}