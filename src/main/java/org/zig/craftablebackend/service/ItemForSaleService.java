package org.zig.craftablebackend.service;

import org.springframework.stereotype.Service;
import org.zig.craftablebackend.infrastructure.dto.ItemForSaleDto;
import org.zig.craftablebackend.infrastructure.entity.ItemForSale;
import org.zig.craftablebackend.infrastructure.entity.ItemPicture;
import org.zig.craftablebackend.infrastructure.repository.ItemForSaleRepository;
import org.zig.craftablebackend.infrastructure.repository.CreatorRepository;
import org.zig.craftablebackend.infrastructure.repository.ItemPictureRepository;

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
        ItemForSale item = new ItemForSale();
        item.setTitle(dto.getTitle());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setCreatorId(creatorRepo.findById(dto.getCreatorId()).orElseThrow());
        ItemForSale saved = itemRepo.save(item);
        dto.setId(saved.getId());
        return dto;
    }

    public Optional<ItemForSaleDto> updateItem(Integer id, ItemForSaleDto dto) {
        return itemRepo.findById(id).map(item -> {
            item.setTitle(dto.getTitle());
            item.setDescription(dto.getDescription());
            item.setPrice(dto.getPrice());
            ItemForSale updated = itemRepo.save(item);
            dto.setId(updated.getId());
            return dto;
        });
    }

    public List<ItemForSaleDto> getAllItems() {
        return itemRepo.findAll().stream().map(item -> {
            ItemForSaleDto dto = new ItemForSaleDto();
            dto.setId(item.getId());
            dto.setTitle(item.getTitle());
            dto.setDescription(item.getDescription());
            dto.setPrice(item.getPrice());
            dto.setCreatorId(item.getCreatorId().getId());
            return dto;
        }).collect(Collectors.toList());
    }

    public Optional<ItemForSaleDto> getItemById(Integer id) {
        return itemRepo.findById(id).map(item -> {
            ItemForSaleDto dto = new ItemForSaleDto();
            dto.setId(item.getId());
            dto.setTitle(item.getTitle());
            dto.setDescription(item.getDescription());
            dto.setPrice(item.getPrice());
            dto.setCreatorId(item.getCreatorId().getId());
            if (item.getItemOrder() != null) {
                dto.setItemOrderId(item.getItemOrder().getId());
            }
            if (item.getItemPictures() != null) {
                dto.setItemPictureIds(item.getItemPictures().stream().map(ItemPicture::getId).toList());
            }
            return dto;
        });
    }


    public void deleteItem(Integer id) {
        itemRepo.deleteById(id);
    }
}
