package com.gemtrading.gem_service.service;

import com.gemtrading.gem_service.dto.GemStoneRequest;
import com.gemtrading.gem_service.dto.GemStoneResponse;
import com.gemtrading.gem_service.entity.GemStone;
import com.gemtrading.gem_service.entity.Tag;
import com.gemtrading.gem_service.exception.ResourceNotFoundException;
import com.gemtrading.gem_service.repository.GemStoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GemStoneService {

    private final GemStoneRepository gemStoneRepository;
    private final TagService tagService;

    public Page<GemStoneResponse> getAllGemStones(Pageable pageable) {
        return gemStoneRepository.findByActiveTrue(pageable).map(item -> toResponse(item));
    }

    public GemStoneResponse createGemstone(GemStoneRequest request) {

        GemStone gemStone = GemStone.builder()
                .gemCode(request.getGemCode())
                .type(request.getType())
                .origin(request.getOrigin())
                .caratWeight(request.getCaratWeight())
                .description(request.getDescription())
                .stockQuantity(request.getStockQuantity())
                .color(request.getColor())
                .treatment(request.getTreatment())
                .pricePerCarat(request.getPricePerCarat())
                .active(true)
                .build();

        return toResponse(gemStoneRepository.save(gemStone));
    }

    @CacheEvict(cacheNames = "gemStoneById" , key = "#gemId")
    public GemStoneResponse addTagToGemStone(Long gemId, Long tagId) {
        GemStone gemStone = gemStoneRepository.findById(tagId).orElseThrow(() ->
                new ResourceNotFoundException(gemId.toString(), "Resource not found"));

        Tag tag = tagService.getTagEntityById(tagId);
        gemStone.getTags().add(tag);
        return toResponse(gemStoneRepository.save(gemStone));

    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "gemStoneById", key = "#id")
    public GemStoneResponse getGemStoneById(Long id) throws ResourceNotFoundException {
        return toResponse(gemStoneRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Gem" , id.toString())));
    }

    @Transactional
    @CachePut(value = "gemStoneById", key = "#id")
    public GemStoneResponse updateGemStone(Long id, GemStoneRequest request) {
        GemStone gemStone = gemStoneRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Gem", id.toString()));

        gemStone.setType(request.getType());
        gemStone.setOrigin(request.getOrigin());
        gemStone.setColor(request.getColor());
        gemStone.setCaratWeight(request.getCaratWeight());
        gemStone.setPricePerCarat(request.getPricePerCarat());
        gemStone.setStockQuantity(request.getStockQuantity());
        gemStone.setDescription(request.getDescription());
        gemStone.setTreatment(request.getTreatment());
        GemStone updated = gemStoneRepository.save(gemStone);
        return toResponse(updated);
    }

    @Transactional
    @CacheEvict(cacheNames = "gemStoneById", key = "#id")
    public void deleteGemStone(Long id) {
        GemStone gemStone = gemStoneRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Gem", id.toString()));
        gemStoneRepository.delete(gemStone);
    }

    private GemStoneResponse toResponse(GemStone gemStone) {

        return GemStoneResponse.builder()
                .id(gemStone.getId())
                .gemCode(gemStone.getGemCode())
                .type(gemStone.getType())
                .color(gemStone.getColor())
                .caratWeight(gemStone.getCaratWeight())
                .origin(gemStone.getOrigin())
                .treatment(gemStone.getTreatment())
                .pricePerCarat(gemStone.getPricePerCarat())
                .stockQuantity(gemStone.getStockQuantity())
                .certified(gemStone.isCertified())
                .description(gemStone.getDescription())
                .createdAt(gemStone.getCreatedAt())
                .updatedAt(gemStone.getUpdatedAt())
                .build();
    }
}
