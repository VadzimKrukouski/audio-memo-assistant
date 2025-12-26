package com.myorg.audiomemo.stt.client.mock;

import com.myorg.audiomemo.stt.client.SttClient;
import com.myorg.audiomemo.stt.dto.SttResult;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MockSttClient implements SttClient {
    @Override
    public SttResult transcribe(UUID audioId) {
        String raw = "{\"text\": \"Test transcription\"}";
        return new SttResult("Test transcription for audio " + audioId, raw);
    }
}
