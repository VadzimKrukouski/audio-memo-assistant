package com.myorg.audiomemo.enrichment.client;

import com.myorg.audiomemo.enrichment.dto.LlmResponse;
import com.myorg.audiomemo.enrichment.dto.TaskItem;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Profile("mock-llm")
public class MockLlmClient implements LlmClient {

    @Override
    public LlmResponse enrich(String transcript) {
        List<TaskItem> taskItems = List.of(
                new TaskItem("Review1", "2025-01-01"),
                new TaskItem("Review2", "2025-01-02")
        );

        Map<String, Object> raw = Map.of("summary", "Summary of the meeting", "length", transcript.length());

        return new LlmResponse(
                "Mock summary for transcript: " + transcript,
                taskItems,
                raw);
    }
}
