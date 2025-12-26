package com.myorg.audiomemo.enrichment.dto;

import java.util.List;
import java.util.Map;

public record LlmResponse (
        String summary,
        List<TaskItem> tasks,
        Map<String, Object> rawJson
){
}
