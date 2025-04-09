package org.zig.craftablebackend.database.dto;

import lombok.Getter;
import lombok.Setter;
import org.zig.craftablebackend.database.shared.UserRole;

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
