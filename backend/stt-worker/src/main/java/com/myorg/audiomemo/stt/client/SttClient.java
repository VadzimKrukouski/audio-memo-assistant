package com.myorg.audiomemo.stt.client;

import com.myorg.audiomemo.stt.dto.SttResult;

import java.util.UUID;

public interface SttClient {
    SttResult transcribe(UUID audioId);
}
