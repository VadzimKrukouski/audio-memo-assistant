package com.myorg.audiomemo.stt.client.mock;

import com.myorg.audiomemo.stt.client.SttClient;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MockSttClient implements SttClient {
    @Override
    public String transcribe(UUID audioId) {
        return "Test transcription for audio " + audioId;
    }
}
