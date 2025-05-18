package org.zig.craftablebackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zig.craftablebackend.infrastructure.dto.ItemForSaleDto;
import org.zig.craftablebackend.service.ItemForSaleService;

import java.util.List;

@RestController
@RequestMapping("/api/itemsforsale")
public class ItemForSaleController {

    private final ItemForSaleService itemService;

    public ItemForSaleController(ItemForSaleService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemForSaleDto> create(@RequestBody ItemForSaleDto dto) {
        return ResponseEntity.ok(itemService.createItem(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemForSaleDto> update(@PathVariable Integer id, @RequestBody ItemForSaleDto dto) {
        return itemService.updateItem(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ItemForSaleDto>> getAll() {
        return ResponseEntity.ok(itemService.getAllItems());
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

}
