package com.puneet.user.service.repository;

import com.puneet.user.service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, String> {
}
