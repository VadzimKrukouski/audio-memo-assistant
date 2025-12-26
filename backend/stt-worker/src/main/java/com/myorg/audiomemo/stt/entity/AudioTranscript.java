package com.myorg.audiomemo.stt.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audio_transcript")
public class AudioTranscript {

    @Id
    private UUID audioId;

    @Column(nullable = false, length = 10000)
    private String text;

    @Column(name = "raw_response", columnDefinition = "TEXT")
    private String rawResponse;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public AudioTranscript() {
        this.audioId = UUID.randomUUID();
    }

    public AudioTranscript(UUID audioId, String text, String rawResponse) {
        this.audioId = audioId;
        this.text = text;
        this.rawResponse = rawResponse;
    }
}
