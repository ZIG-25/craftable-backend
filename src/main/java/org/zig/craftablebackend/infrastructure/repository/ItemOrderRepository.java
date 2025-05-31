package org.zig.craftablebackend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.zig.craftablebackend.infrastructure.entity.ItemOrder;

import java.util.Optional;

@Repository
public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer>, JpaSpecificationExecutor<ItemOrder> {
    Optional<ItemOrder> findItemOrderById(Integer integer);
}