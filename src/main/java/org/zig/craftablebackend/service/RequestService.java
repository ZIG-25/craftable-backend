package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.dto.RequestDto;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.Customer;
import org.zig.craftablebackend.infrastructure.entity.Request;
import org.zig.craftablebackend.infrastructure.repository.CreatorRepository;
import org.zig.craftablebackend.infrastructure.repository.CustomerRepository;
import org.zig.craftablebackend.infrastructure.repository.RequestRepository;

@Service
public class RequestService {
    private final CreatorRepository creatorRepository;
    private final CustomerRepository customerRepository;
    private final RequestRepository requestRepository;

    @Autowired
    public RequestService(CreatorRepository creatorRepository, CustomerRepository customerRepository, RequestRepository requestRepository) {
        this.creatorRepository = creatorRepository;
        this.customerRepository = customerRepository;
        this.requestRepository = requestRepository;
    }

    public RequestDto createNew(RequestDto requestDto, String customerEmail) {
        Customer customer = customerRepository
                .findCustomerByEmail(customerEmail)
                .orElseThrow(() -> new IllegalArgumentException("No such user"));
        Creator creator = creatorRepository
                .findCreatorById(requestDto.getCreatorId().getId())
                .orElseThrow(() -> new IllegalArgumentException("No such creator"));
        Request request = RequestDto.fromDto(requestDto);
        request.setCreatorId(creator);
        request.setCustomerId(customer);
        Request save = requestRepository.save(request);
        return RequestDto.toDto(save);
    }

    public RequestDto updateStatus(RequestDto requestDto) {
        Request request = requestRepository.findById(
                requestDto.getId()
        ).orElseThrow(() -> new IllegalArgumentException("No such request"));
        request.setStatus(requestDto.getStatus());
        requestRepository.save(request);
        return RequestDto.toDto(request);
    }
}
