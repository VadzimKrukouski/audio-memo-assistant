package com.myorg.audiodemo.upload.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audio_meta")
public class AudioMeta {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "original_file_name", nullable = false, length = 255)
    private String originalFileName;

    @Column(name = "content_type", nullable = false, length = 100)
    private String contentType;

    @Column(name = "size", nullable = false)
    private long size;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public AudioMeta() {
        this.id = UUID.randomUUID();
    }

    public AudioMeta(UUID id, String originalFileName, String contentType, long size) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.size = size;
    }
}
