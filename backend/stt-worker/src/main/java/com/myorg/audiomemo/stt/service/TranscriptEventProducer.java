package com.myorg.audiomemo.stt.service;

import com.myorg.audiomemo.stt.dto.TranscriptProducedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TranscriptEventProducer {

    private final KafkaTemplate<String, TranscriptProducedEvent> kafkaTemplate;

    public TranscriptEventProducer(KafkaTemplate<String, TranscriptProducedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(TranscriptProducedEvent event) {
        kafkaTemplate.send("audio.transcribed", event.audioId().toString(), event);
    }
}
