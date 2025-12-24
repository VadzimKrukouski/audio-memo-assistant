package com.myorg.audiomemo.stt.dto;

import java.util.UUID;

public record TranscriptProducedEvent (
    UUID audioId,
    String transcript
) {}
