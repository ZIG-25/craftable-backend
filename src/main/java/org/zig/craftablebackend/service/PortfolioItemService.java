package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zig.craftablebackend.dto.PortfolioItemDto;
import org.zig.craftablebackend.infrastructure.entity.Creator;
import org.zig.craftablebackend.infrastructure.entity.PortfolioItem;
import org.zig.craftablebackend.infrastructure.repository.CreatorRepository;
import org.zig.craftablebackend.infrastructure.repository.PortfolioItemRepository;
import org.zig.craftablebackend.shared.TokenUtils;

import java.util.List;

@Service
public class PortfolioItemService {
    private final PortfolioItemRepository portfolioItemRepository;
    private final CreatorRepository creatorRepository;
    private final PictureSevice pictureSevice;


    @Autowired
    public PortfolioItemService(PortfolioItemRepository portfolioItemRepository,
                                CreatorRepository creatorRepository, PictureSevice pictureSevice) {
        this.portfolioItemRepository = portfolioItemRepository;
        this.creatorRepository = creatorRepository;
        this.pictureSevice = pictureSevice;
    }

    public List<PortfolioItem> getAll() {
        return portfolioItemRepository.findAll();
    }

    public PortfolioItem getById(long id) {
        return portfolioItemRepository.findById(id).orElseThrow(() -> new RuntimeException("PortfolioItem not found"));
    }

    public PortfolioItemDto create(String email, PortfolioItemDto portfolioItem) {
        Creator creator = creatorRepository.findCreatorByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid creator"));

        PortfolioItem item = PortfolioItemDto.fromDto(portfolioItem);
        item.setPhotoUrl(pictureSevice.savePicture(portfolioItem.getPhotoUrl()));
        item.setCreatorId(creator);

        PortfolioItem save = portfolioItemRepository.save(item);
        return PortfolioItemDto.toDto(save);
    }

    public void delete(long id) {
        portfolioItemRepository.deleteById(id);
    }

    public List<PortfolioItem> getByCreator(Creator creator) {
        return portfolioItemRepository.findByCreatorId(creator);
    }
}
