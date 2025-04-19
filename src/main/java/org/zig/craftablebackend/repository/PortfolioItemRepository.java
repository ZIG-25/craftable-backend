package org.zig.craftablebackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zig.craftablebackend.database.Creator;
import org.zig.craftablebackend.database.PortfolioItem;

import java.util.List;

@Repository
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
    List<PortfolioItem> findByCreatorId(Creator creator);
}
