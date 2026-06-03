package com.gemtrading.gem_service.service;

import com.gemtrading.gem_service.dto.TagRequest;
import com.gemtrading.gem_service.dto.TagResponse;
import com.gemtrading.gem_service.entity.Tag;
import com.gemtrading.gem_service.exception.ResourceNotFoundException;
import com.gemtrading.gem_service.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public TagResponse createTag(TagRequest request) {

        if (tagRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Tag already exists with name: " + request.getName());
        }

        Tag tag = Tag.builder()
                .name(request.getName().trim())
                .description(request.getDescription())
                .build();

        Tag savedTag = tagRepository.save(tag);

        return mapToResponse(savedTag);
    }

    public Tag getTagEntityById(Long tagId) {
        return tagRepository.findById(tagId).orElseThrow(()
                -> new ResourceNotFoundException(tagId.toString(), "resource not found"));
    }

    public List<TagResponse> getAllTags() {
        return tagRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TagResponse getTagById(Long id) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tag not found with id: " + id));

        return mapToResponse(tag);
    }

    public TagResponse updateTag(Long id, TagRequest request) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tag not found with id: " + id));

        tagRepository.findByNameIgnoreCase(request.getName())
                .ifPresent(existingTag -> {
                    if (!existingTag.getId().equals(id)) {
                        throw new IllegalArgumentException(
                                "Another tag already exists with name: " + request.getName()
                        );
                    }
                });

        tag.setName(request.getName().trim());
        tag.setDescription(request.getDescription());

        Tag updatedTag = tagRepository.save(tag);

        return mapToResponse(updatedTag);
    }

    public void deleteTag(Long id) {

        Tag tag = tagRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tag not found with id: " + id));

        tagRepository.delete(tag);
    }

    private TagResponse mapToResponse(Tag tag) {
        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .description(tag.getDescription())
                .createdAt(tag.getCreatedAt())
                .build();
    }
}