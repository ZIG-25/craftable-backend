package org.zig.craftablebackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.zig.craftablebackend.database.Creator;
import org.zig.craftablebackend.database.PortfolioItem;
import org.zig.craftablebackend.repository.CreatorRepository;
import org.zig.craftablebackend.repository.PortfolioItemRepository;
import org.zig.craftablebackend.shared.TokenUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class PortfolioItemService {
    private final PortfolioItemRepository portfolioItemRepository;
    private final CreatorRepository creatorRepository;
    private final TokenUtils tokenUtils;

    private static final Logger logger = LoggerFactory.getLogger(PortfolioItemService.class);

    @Autowired
    public PortfolioItemService(PortfolioItemRepository portfolioItemRepository,
                                CreatorRepository creatorRepository,
                                TokenUtils tokenUtils) {
        this.portfolioItemRepository = portfolioItemRepository;
        this.creatorRepository = creatorRepository;
        this.tokenUtils = tokenUtils;
    }

    public List<PortfolioItem> getAll() {
        return portfolioItemRepository.findAll();
    }

    public PortfolioItem getById(long id) {
        return portfolioItemRepository.findById(id).orElseThrow(() -> new RuntimeException("PortfolioItem not found"));
    }

    public PortfolioItem create(String authHeader, PortfolioItem portfolioItem) {
        String token = authHeader.replace("Bearer ", "");

        logger.info("🔑 Token received: {}", token);
        String email = tokenUtils.getEmailFromToken(token);
        logger.info("🔍 Looking up creator with email: {}", email);
        Creator creator = creatorRepository.findCreatorByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid creator"));

        portfolioItem.setCreatorId(creator);

        return portfolioItemRepository.save(portfolioItem);
    }

    public void delete(long id) {
        portfolioItemRepository.deleteById(id);
    }

    public List<PortfolioItem> getByCreator(Creator creator) {
        return portfolioItemRepository.findByCreatorId(creator);
    }
}
