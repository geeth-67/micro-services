package com.gemtrading.gem_service.service;

import com.gemtrading.gem_service.dto.GemStoneRequest;
import com.gemtrading.gem_service.dto.GemStoneResponse;
import com.gemtrading.gem_service.entity.GemStone;
import com.gemtrading.gem_service.entity.Tag;
import com.gemtrading.gem_service.exception.ResourceNotFoundException;
import com.gemtrading.gem_service.repository.GemStoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public GemStoneResponse addTagToGemStone(Long gemId, Long tagId) {
        GemStone gemStone = gemStoneRepository.findById(tagId).orElseThrow(() ->
                new ResourceNotFoundException(gemId.toString(), "Resource not found"));

        Tag tag = tagService.getTagEntityById(tagId);
        gemStone.getTags().add(tag);
        return toResponse(gemStoneRepository.save(gemStone));

    }

    public GemStoneResponse getGemStoneById(Long id) throws ResourceNotFoundException {
        return toResponse(gemStoneRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Gem" , id.toString())));
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
