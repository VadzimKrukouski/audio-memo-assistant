package com.myorg.audiomemo.enrichment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.myorg.audiomemo.enrichment.dto.TranscriptProducedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TranscriptEventListener {

    private final EnrichmentService enrichmentService;

    public TranscriptEventListener(EnrichmentService enrichmentService) {
        this.enrichmentService = enrichmentService;
    }

    @KafkaListener(topics = "audio.transcribed")
    public void handle(TranscriptProducedEvent event) throws JsonProcessingException {
        enrichmentService.process(event);
    }
}
