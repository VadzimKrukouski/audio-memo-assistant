package com.myorg.audiomemo.enrichment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myorg.audiomemo.enrichment.client.LlmClient;
import com.myorg.audiomemo.enrichment.dto.LlmResponse;
import com.myorg.audiomemo.enrichment.dto.TranscriptProducedEvent;
import com.myorg.audiomemo.enrichment.entity.EnrichmentResult;
import com.myorg.audiomemo.enrichment.repository.EnrichmentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnrichmentService {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final LlmClient llmClient;
    private final EnrichmentRepository repository;

    public EnrichmentService(LlmClient llmClient, EnrichmentRepository enrichmentRepository) {
        this.llmClient = llmClient;
        this.repository = enrichmentRepository;
    }

    public void process(TranscriptProducedEvent event) throws JsonProcessingException {

        LlmResponse response = llmClient.enrich(event.transcript());
        UUID uuid = UUID.randomUUID();
        EnrichmentResult enrichmentResult = new EnrichmentResult(
                uuid,
                event.audioId(),
                response.summary(),
                mapper.writeValueAsString(response.tasks()),
                mapper.writeValueAsString(response.rawJson())
                );
        repository.save(enrichmentResult);
    }
}
