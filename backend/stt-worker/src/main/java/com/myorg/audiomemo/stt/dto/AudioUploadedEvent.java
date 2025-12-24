package com.myorg.audiomemo.stt.dto;

import java.util.UUID;

public record AudioUploadedEvent(
        UUID audioId,
        String bucket,
        String objectName
) {
}
