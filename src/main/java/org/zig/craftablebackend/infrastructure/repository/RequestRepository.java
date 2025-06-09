package org.zig.craftablebackend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zig.craftablebackend.infrastructure.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
