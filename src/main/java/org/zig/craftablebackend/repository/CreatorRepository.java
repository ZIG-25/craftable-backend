package org.zig.craftablebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zig.craftablebackend.database.Creator;

import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {
    Optional<Creator> findCreatorByEmail(String email);
}
