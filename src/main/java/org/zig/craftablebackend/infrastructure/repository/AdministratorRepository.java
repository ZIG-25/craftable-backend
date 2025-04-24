package org.zig.craftablebackend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zig.craftablebackend.infrastructure.entity.Administrator;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
Optional<Administrator> findByEmail(String email);
}
