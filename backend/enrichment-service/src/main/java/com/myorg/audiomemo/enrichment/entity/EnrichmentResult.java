package com.myorg.audiomemo.enrichment.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "enrichment_result")
public class EnrichmentResult {

    @Id
    private UUID id;

    @Column(name = "audio_id", nullable = false)
    private UUID audioId;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "tasks", columnDefinition = "TEXT")
    private String tasks;

    @Column(name = "raw_llm_response", columnDefinition = "TEXT")
    private String rawLlmResponse;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public EnrichmentResult() {
    }

    public EnrichmentResult(UUID id, UUID audioId, String summary, String tasks, String rawLlmResponse) {
        this.id = id;
        this.audioId = audioId;
        this.summary = summary;
        this.tasks = tasks;
        this.rawLlmResponse = rawLlmResponse;
    }

}
