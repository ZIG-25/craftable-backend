package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zig.craftablebackend.dto.CreatorDto;
import org.zig.craftablebackend.dto.ProfessionDto;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.Profession;
import org.zig.craftablebackend.infrastructure.repository.CreatorRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public CreatorDto updateCreator(CreatorDto creatorDto) {
        Creator creator = creatorRepository.findCreatorByEmail(creatorDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("No such creator"));
        creator.setBio(creatorDto.getBio());
        creator.setPhoneNumber(creatorDto.getPhoneNumber());
        creator.getProfessions().clear();
        creator.getProfessions().addAll(
                creatorDto.getProfessions().stream().map(ProfessionDto::fromDto).peek(it -> it.setCreatorId(creator)).collect(Collectors.toList())
        );
        System.out.println(creator);
        Creator save = creatorRepository.save(creator);
        return CreatorDto.toFullDto(save);
    }
}
