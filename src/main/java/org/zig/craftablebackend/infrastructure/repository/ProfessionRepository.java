package org.zig.craftablebackend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zig.craftablebackend.infrastructure.entity.Profession;

public interface ProfessionRepository extends JpaRepository<Profession, Integer> {
}
