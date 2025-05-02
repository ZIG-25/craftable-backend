package org.zig.craftablebackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestDto {
    private Integer id;
    private Integer price;
    private String description;
    private String title;
    private String status;
    private Date deadline;

    private Integer customerId;
    private Integer creatorId;
}
