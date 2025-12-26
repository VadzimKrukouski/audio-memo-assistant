package com.myorg.audiomemo.enrichment.repository;

import com.myorg.audiomemo.enrichment.entity.EnrichmentResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnrichmentRepository extends JpaRepository<EnrichmentResult, UUID> {
}
