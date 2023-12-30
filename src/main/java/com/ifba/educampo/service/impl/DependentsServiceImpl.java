package com.ifba.educampo.service.impl;

import com.ifba.educampo.annotation.Log;
import com.ifba.educampo.dto.associate.dependents.DependentsPostDto;
import com.ifba.educampo.dto.associate.dependents.DependentsPutDto;
import com.ifba.educampo.entity.associate.Dependents;
import com.ifba.educampo.mapper.associate.DependentsMapper;
import com.ifba.educampo.repository.DependentsRepository;
import com.ifba.educampo.service.DependentsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Log
public class DependentsServiceImpl implements DependentsService {
    private final DependentsMapper dependentsMapper;
    private final DependentsRepository dependentsRepository;

    public Dependents save(DependentsPostDto dto) {
        log.info("Saving dependent.");
        return dependentsRepository.save(dependentsMapper.postDtoToEntity(dto));
    }

    public Dependents update(Long id, DependentsPutDto dto) {
        log.info("Replacing dependent with ID: {}", id);

        Dependents dependents = dependentsRepository.getReferenceById(id);
        dependents.update(dependentsMapper.putDtoToEntity(dto));

        return dependents;
    }

    public void delete(Long id) {
        log.info("Deleting dependent with ID: {}", id);
        Dependents dependents = dependentsRepository.getReferenceById(id);
        dependents.delete();
    }
}