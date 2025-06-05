package org.zig.craftablebackend.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.infrastructure.dto.ItemForSaleDto;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.ItemForSale;
import org.zig.craftablebackend.infrastructure.entity.ItemPicture;
import org.zig.craftablebackend.infrastructure.repository.ItemForSaleRepository;
import org.zig.craftablebackend.infrastructure.repository.CreatorRepository;
import org.zig.craftablebackend.infrastructure.repository.ItemPictureRepository;
import org.zig.craftablebackend.infrastructure.specification.ItemForSaleSpecification;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemForSaleService {
    private final ItemForSaleRepository itemRepo;
    private final CreatorRepository creatorRepo;
    private final ItemPictureRepository pictureRepo;

    public ItemForSaleService(ItemForSaleRepository itemRepo, CreatorRepository creatorRepo, ItemPictureRepository pictureRepo) {
        this.itemRepo = itemRepo;
        this.creatorRepo = creatorRepo;
        this.pictureRepo = pictureRepo;
    }

    //to do - ogarnąć zdjęcia
    public ItemForSaleDto createItem(ItemForSaleDto dto) {
        Creator creator = creatorRepo.findById(dto.getCreatorId()).orElseThrow();
        ItemForSale saved = itemRepo.save(ItemForSaleDto.toEntity(dto, creator));
        return ItemForSaleDto.fromEntity(saved);
    }

    public Optional<ItemForSaleDto> updateItem(Integer id, ItemForSaleDto dto) {
        return itemRepo.findById(id)
                .map(item -> {
                    ItemForSaleDto.updateEntity(dto, item);
                    ItemForSale updated = itemRepo.save(item);
                    return ItemForSaleDto.fromEntity(updated);
                });
    }

    public List<ItemForSaleDto> getAllItems() {
        return itemRepo.findAll().stream()
                .map(ItemForSaleDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<ItemForSaleDto> getItemById(Integer id) {
        return itemRepo.findById(id)
                .map(ItemForSaleDto::fromEntity);
    }

    public List<ItemForSaleDto> getFilteredItems(
            Integer creatorId,
            String professionName,
            Integer minPrice,
            Integer maxPrice
    ) {
        Specification<ItemForSale> spec = Specification.where(ItemForSaleSpecification.hasCreatorId(creatorId))
                .and(ItemForSaleSpecification.hasProfession(professionName))
                .and(ItemForSaleSpecification.priceBetween(minPrice, maxPrice));

        return itemRepo.findAll(spec).stream()
                .map(ItemForSaleDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ItemForSaleDto> getItemsByArtistId(Integer creatorId) {
        return itemRepo.findByCreatorId_Id(creatorId).stream()
                .map(ItemForSaleDto::fromEntity)
                .collect(Collectors.toList());
    }



    public void deleteItem(Integer id) {
        itemRepo.deleteById(id);
    }
}
