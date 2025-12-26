package com.myorg.audiomemo.enrichment.dto;

import java.util.UUID;

public record TranscriptProducedEvent(
        UUID audioId,
        String transcript
) {
}
