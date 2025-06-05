package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.dto.CreatorDto;
import org.zig.craftablebackend.infrastructure.repository.CreatorRepository;

import java.util.List;

@Service
public class CreatorService {
    private final CreatorRepository creatorRepository;
    private final PictureSevice pictureSevice;

    @Autowired
    public CreatorService(CreatorRepository creatorRepository, PictureSevice pictureSevice) {
        this.creatorRepository = creatorRepository;
        this.pictureSevice = pictureSevice;
    }

    public List<CreatorDto> getAll() {
        return creatorRepository.findAll()
                .stream()
                .map(CreatorDto::toDto)
                .toList();
    }

    public CreatorDto getOne(Integer id) {
        return CreatorDto.toFullDto(
                creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No such creator"))
        );

    }

    public CreatorDto getOneByEmail(String email) {

        return CreatorDto.toFullDto(
                creatorRepository.findCreatorByEmail(email)
                        .orElseThrow(() -> new RuntimeException("No such creator"))
        );
    }
}
