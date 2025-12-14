package com.myorg.audiodemo.upload.dto;

import java.util.UUID;

public record AudioUploadedEvent(
        UUID audioId,
        String bucket,
        String objectName
) {
}
