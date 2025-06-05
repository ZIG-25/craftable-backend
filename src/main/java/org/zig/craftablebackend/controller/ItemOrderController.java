package org.zig.craftablebackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zig.craftablebackend.infrastructure.entity.ItemOrder;
import org.zig.craftablebackend.service.ItemOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class ItemOrderController {
    private final ItemOrderService itemOrderService;

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
    public ResponseEntity<ItemOrder> createOrder(@RequestBody ItemOrder order,
                                                 @RequestParam Integer customerId,
                                                 @RequestParam Integer itemForSaleId) {
        try {
            return ResponseEntity.ok(itemOrderService.createOrder(order, customerId, itemForSaleId));
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
