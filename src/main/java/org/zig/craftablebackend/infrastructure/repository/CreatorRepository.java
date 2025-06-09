package org.zig.craftablebackend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zig.craftablebackend.infrastructure.entity.Creator;

import java.util.List;
import java.util.Optional;

public interface CreatorRepository extends JpaRepository<Creator, Integer> {
    Optional<Creator> findCreatorByEmail(String email);

    Optional<Creator> findCreatorById(Integer id);
}
