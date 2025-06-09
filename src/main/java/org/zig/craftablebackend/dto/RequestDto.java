package org.zig.craftablebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.zig.craftablebackend.infrastructure.entity.Request;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Integer id;
    private Integer price;
    private String description;
    private String title;
    private String status;
    private Date deadline;

    private CustomerDto customerId;
    private CreatorDto creatorId;

    public static RequestDto toDto(Request request) {
        return new RequestDto(
                request.getId(),
                request.getPrice(),
                request.getDescription(),
                request.getTitle(),
                request.getStatus(),
                request.getDeadline(),
                CustomerDto.fromEntity(request.getCustomerId()),
                CreatorDto.toDto(request.getCreatorId())
        );
    }

    public static Request fromDto(RequestDto requestDto) {
        Request request = new Request();
        request.setId(requestDto.getId());
        request.setPrice(requestDto.getPrice());
        request.setDescription(requestDto.getDescription());
        request.setTitle(requestDto.getTitle());
        request.setStatus(requestDto.getStatus());
        request.setDeadline(requestDto.getDeadline());
        return request;
    }


}
