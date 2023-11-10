package com.ssafy.mockstockinvestment.user.domain.repository;

import java.util.Optional;

import com.ssafy.mockstockinvestment.user.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByEmail(String email);

    Optional<Manager> findByEmailAndPassword(String email, String password);
}