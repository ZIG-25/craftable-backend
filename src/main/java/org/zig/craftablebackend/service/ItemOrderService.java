package org.zig.craftablebackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.infrastructure.entity.ItemOrder;
import org.zig.craftablebackend.infrastructure.entity.Customer;
import org.zig.craftablebackend.infrastructure.entity.ItemForSale;
import org.zig.craftablebackend.infrastructure.repository.CustomerRepository;
import org.zig.craftablebackend.infrastructure.repository.ItemForSaleRepository;
import org.zig.craftablebackend.infrastructure.repository.ItemOrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemOrderService {
    private final ItemOrderRepository itemOrderRepository;
    private final CustomerRepository customerRepository;
    private final ItemForSaleRepository itemForSaleRepository;

    public List<ItemOrder> getAllOrders() {
        return itemOrderRepository.findAll();
    }

    public Optional<ItemOrder> getOrderById(Integer id) {
        return itemOrderRepository.findById(id);
    }

    public ItemOrder createOrder(ItemOrder order, Integer customerId, Integer itemForSaleId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        ItemForSale item = itemForSaleRepository.findById(itemForSaleId)
                .orElseThrow(() -> new IllegalArgumentException("ItemForSale not found"));

        order.setCustomerId(customer);
        order.setItemForSale(item);
        return itemOrderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        itemOrderRepository.deleteById(id);
    }

    public ItemOrder updateOrder(Integer id, ItemOrder updatedOrder) {
        ItemOrder existing = itemOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        existing.setDeliveryAddress(updatedOrder.getDeliveryAddress());
        existing.setStatus(updatedOrder.getStatus());
        existing.setDate(updatedOrder.getDate());
        return itemOrderRepository.save(existing);
    }
}
