package com.myorg.audiomemo.stt.client;

import java.util.UUID;

public interface SttClient {
    String transcribe(UUID audioId);
}
