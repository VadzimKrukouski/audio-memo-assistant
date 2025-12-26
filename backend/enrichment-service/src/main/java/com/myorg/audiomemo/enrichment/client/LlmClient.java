package com.myorg.audiomemo.enrichment.client;

import com.myorg.audiomemo.enrichment.dto.LlmResponse;

public interface LlmClient {

    LlmResponse enrich(String transcript);

}
