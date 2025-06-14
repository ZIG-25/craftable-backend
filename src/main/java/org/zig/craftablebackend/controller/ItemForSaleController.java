package org.zig.craftablebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zig.craftablebackend.dto.ItemForSaleDto;
import org.zig.craftablebackend.service.ItemForSaleService;

import java.util.List;

@RestController
@RequestMapping("/api/itemsforsale")
public class ItemForSaleController {

    private final ItemForSaleService itemService;

    @Autowired
    public ItemForSaleController(ItemForSaleService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemForSaleDto> create(@RequestBody ItemForSaleDto dto) {
        return ResponseEntity.ok(itemService.createItem(dto));
    }

//    @PutMapping("/update")
//    public ResponseEntity<ItemForSaleDto> update(@RequestBody ItemForSaleDto dto) {
//        return itemService.updateItem(dto)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping
    public ResponseEntity<List<ItemForSaleDto>> getAll(
            @RequestParam(required = false) Integer creatorId,
            @RequestParam(required = false) String professionName,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice
    ) {
        return ResponseEntity.ok(itemService.getFilteredItems(creatorId, professionName, minPrice, maxPrice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemForSaleDto> getById(@PathVariable Integer id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/by-artist/{creatorId}")
    public ResponseEntity<List<ItemForSaleDto>> getByArtist(@PathVariable Integer creatorId) {
        return ResponseEntity.ok(itemService.getItemsByArtistId(creatorId));
    }


}
