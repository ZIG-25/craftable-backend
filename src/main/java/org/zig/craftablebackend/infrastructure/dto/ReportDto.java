package org.zig.craftablebackend.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.shared.UserRole;

import java.util.Date;

@Getter
@Setter
public class ReportDto {
    private Integer id;
    private String description;
    private String title;
    private String status;
    private Date date;
    private UserRole targetedUserRole;

    private Integer creatorId;
    private Integer customerId;
}
