package com.ifba.educampo.repository;

import com.ifba.educampo.entity.associate.Affiliation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffiliationRepository extends JpaRepository<Affiliation, Long> {
}