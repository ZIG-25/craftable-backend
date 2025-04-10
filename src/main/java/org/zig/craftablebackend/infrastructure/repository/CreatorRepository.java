package org.zig.craftablebackend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zig.craftablebackend.infrastructure.database.Creator;

import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {
    Optional<Creator> findByLogin(String login);
}
