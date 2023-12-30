package com.ifba.educampo.service.impl;

import com.ifba.educampo.annotation.Log;
import com.ifba.educampo.dto.associate.affiliation.AffiliationPostDto;
import com.ifba.educampo.dto.associate.affiliation.AffiliationPutDto;
import com.ifba.educampo.entity.associate.Affiliation;
import com.ifba.educampo.mapper.associate.AffiliationMapper;
import com.ifba.educampo.repository.AffiliationRepository;
import com.ifba.educampo.service.AffiliationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Log
public class AffiliationServiceImpl implements AffiliationService {
    private final AffiliationMapper affiliationMapper;
    private final AffiliationRepository affiliationRepository;

    public Affiliation save(AffiliationPostDto dto) {
        log.info("Saving affiliation.");
        return affiliationRepository.save(affiliationMapper.postDtoToEntity(dto));
    }

    public Affiliation update(Long id, AffiliationPutDto dto) {
        log.info("Replacing affiliation with ID: {}", id);

        Affiliation affiliation = affiliationRepository.getReferenceById(id);
        affiliation.update(affiliationMapper.putDtoToEntity(dto));

        return affiliation;
    }

    public void delete(Long id) {
        log.info("Deleting affiliation with ID: {}", id);
        Affiliation affiliation = affiliationRepository.getReferenceById(id);
        affiliation.delete();
    }
}