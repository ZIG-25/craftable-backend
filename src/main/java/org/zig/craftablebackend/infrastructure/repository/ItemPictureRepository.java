package org.zig.craftablebackend.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zig.craftablebackend.infrastructure.entity.ItemPicture;

import java.util.Optional;

@Repository
public interface ItemPictureRepository extends JpaRepository<ItemPicture, Integer> {
    Optional<ItemPicture> findItemPictureById(Integer integer);
}
