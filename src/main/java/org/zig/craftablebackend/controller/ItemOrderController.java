package org.zig.craftablebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zig.craftablebackend.dto.ItemOrderDto;
import org.zig.craftablebackend.infrastructure.entity.ItemOrder;
import org.zig.craftablebackend.service.ItemOrderService;
import org.zig.craftablebackend.service.JwtService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class ItemOrderController {
    private final ItemOrderService itemOrderService;
    private final JwtService jwtService;

    @GetMapping
    public List<ItemOrder> getAllOrders() {
        return itemOrderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemOrder> getOrderById(@PathVariable Integer id) {
        return itemOrderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemOrderDto> createOrder(@RequestBody ItemOrderDto itemOrderDto, @RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.ok(itemOrderService.createOrder(itemOrderDto, jwtService.extractEmail(headers)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/by-artist")
    public ResponseEntity<List<ItemOrderDto>> getAllOrdersByArtist(@RequestHeader HttpHeaders headers) {
        try {
            return ResponseEntity.ok(itemOrderService.getForArtist(jwtService.extractEmail(headers)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemOrder> updateOrder(@PathVariable Integer id,
                                                 @RequestBody ItemOrder updatedOrder) {
        try {
            return ResponseEntity.ok(itemOrderService.updateOrder(id, updatedOrder));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        itemOrderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
