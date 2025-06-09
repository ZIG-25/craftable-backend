package org.zig.craftablebackend.infrastructure.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.PortfolioItem;

import java.util.List;

@Repository
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
    List<PortfolioItem> findByCreatorId(Creator creator);
}
