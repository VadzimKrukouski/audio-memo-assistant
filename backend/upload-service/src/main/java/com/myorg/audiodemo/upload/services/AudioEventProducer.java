package com.myorg.audiodemo.upload.services;

import com.myorg.audiodemo.upload.dto.AudioUploadedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AudioEventProducer {

    private final KafkaTemplate<String, AudioUploadedEvent> kafkaTemplate;

    public AudioEventProducer(KafkaTemplate<String, AudioUploadedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(AudioUploadedEvent event) {
        kafkaTemplate.send("audio.uploaded", event.audioId().toString(), event);
    }
}
