package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.entity.ItemOrder;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderDto {
    private Integer id;
    private String deliveryAddress;
    private Date date;
    private String status;

    private CustomerDto customerId;
    private ItemForSaleDto itemForSaleId;

    public static ItemOrderDto toDto(ItemOrder itemOrder) {
        return  new ItemOrderDto(
                itemOrder.getId(),
                itemOrder.getDeliveryAddress(),
                itemOrder.getDate(),
                itemOrder.getStatus(),
                CustomerDto.fromEntity(itemOrder.getCustomerId()),
                ItemForSaleDto.toDto(itemOrder.getItemForSale())
        );
    }

    public static ItemOrder fromDto(ItemOrderDto itemOrderDto) {
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setId(itemOrderDto.getId());
        itemOrder.setDeliveryAddress(itemOrderDto.getDeliveryAddress());
        itemOrder.setDate(itemOrderDto.getDate());
        itemOrder.setStatus(itemOrderDto.getStatus());

        return itemOrder;
    }

}
