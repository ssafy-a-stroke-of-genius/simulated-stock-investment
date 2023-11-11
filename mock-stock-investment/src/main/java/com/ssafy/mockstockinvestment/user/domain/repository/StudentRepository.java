package com.ssafy.mockstockinvestment.user.domain.repository;

import java.util.Optional;

import com.ssafy.mockstockinvestment.user.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByEmailAndPassword(String email, String password);
}