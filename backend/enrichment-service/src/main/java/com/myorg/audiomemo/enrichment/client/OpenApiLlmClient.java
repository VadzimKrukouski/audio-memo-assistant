package com.myorg.audiomemo.enrichment.client;

import com.myorg.audiomemo.enrichment.dto.LlmResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
@Profile("openapi-llm")
public class OpenApiLlmClient implements LlmClient {

    private final ChatClient chatClient;

    public OpenApiLlmClient(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public LlmResponse enrich(String transcript) {
        var systemPrompt = """
                You are an AI assistant that summarizes meeting transcripts and extracts action items.
                Provide the summary and action items in the following JSON format:
                {
                  "summary": "Brief summary of the meeting",
                  "tasks": [
                    "Action item 1",
                    "Action item 2"
                  ]
                }
                Ensure the JSON is well-formed.
                """;
        return chatClient
                .prompt()
                .system(systemPrompt)
                .user(transcript)
                .call()
                .entity(LlmResponse.class);
    }

    private Map<String, Object> buildPrompt(String transcript) {
        return Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "system", "content", "You summarize meetings"),
                        Map.of("role", "user", "content", "Summarize and extract action items as JSON:\n" + transcript))
        );
    }
}
