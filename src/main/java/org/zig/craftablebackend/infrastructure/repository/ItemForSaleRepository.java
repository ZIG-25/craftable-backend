package org.zig.craftablebackend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zig.craftablebackend.infrastructure.entity.ItemForSale;

import java.util.Optional;

@Repository
public interface ItemForSaleRepository extends JpaRepository<ItemForSale, Integer> {
    Optional<ItemForSale> findItemForSaleById(Integer integer);
}
