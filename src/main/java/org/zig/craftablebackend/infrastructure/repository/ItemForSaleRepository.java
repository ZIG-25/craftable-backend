package org.zig.craftablebackend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.zig.craftablebackend.infrastructure.entity.ItemForSale;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemForSaleRepository extends JpaRepository<ItemForSale, Integer>, JpaSpecificationExecutor<ItemForSale> {
    Optional<ItemForSale> findItemForSaleById(Integer integer);

    // nazwa taka ze względu na nazwę pola creator w ItemForSale entity
    List<ItemForSale> findByCreatorId_Id(Integer creatorId);
}
