package com.myorg.audiodemo.upload.services;

import com.myorg.audiodemo.upload.dto.AudioUploadedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class AudioEventProducer {

    private static final Logger LOG = LoggerFactory.getLogger(AudioEventProducer.class);

    private final KafkaTemplate<String, AudioUploadedEvent> kafkaTemplate;

    public AudioEventProducer(KafkaTemplate<String, AudioUploadedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(AudioUploadedEvent event) {
            LOG.info("Sending event: {}", event);
            kafkaTemplate.send("audio.uploaded", event.audioId().toString(), event)
                    .whenComplete((res, e) -> {
                        if (e != null) LOG.error("Error sending event", e);
                        else LOG.info("Event sent successfully to partition {}", res.getRecordMetadata().partition());
                    }
            );
    }
}
