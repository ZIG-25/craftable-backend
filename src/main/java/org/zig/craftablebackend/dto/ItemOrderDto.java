package org.zig.craftablebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ItemOrderDto {
    private Integer id;
    private String deliveryAddress;
    private Date date;
    private String status;

    private Integer customerId;
    private Integer itemForSaleId;
}
