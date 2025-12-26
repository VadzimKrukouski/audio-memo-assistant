package com.myorg.audiomemo.stt.service;

import com.myorg.audiomemo.stt.client.SttClient;
import com.myorg.audiomemo.stt.dto.AudioUploadedEvent;
import com.myorg.audiomemo.stt.dto.SttResult;
import com.myorg.audiomemo.stt.dto.TranscriptProducedEvent;
import com.myorg.audiomemo.stt.entity.AudioTranscript;
import com.myorg.audiomemo.stt.repository.AudioTranscriptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AudioUploadedListener {

    private static final Logger LOG = LoggerFactory.getLogger(AudioUploadedListener.class);

    private final SttClient sttClient;
    private final AudioTranscriptRepository repository;
    private final TranscriptEventProducer producer;

    public AudioUploadedListener(SttClient sttClient,
                                 AudioTranscriptRepository repository,
                                 TranscriptEventProducer producer) {
        this.sttClient = sttClient;
        this.repository = repository;
        this.producer = producer;
    }

    @KafkaListener(topics = "audio.uploaded")
    public void listen(AudioUploadedEvent event) {
        LOG.info("Received event: {}", event);
        SttResult result = sttClient.transcribe(event.audioId());

        repository.save(new AudioTranscript(event.audioId(), result.transcript(), result.rawJson()));

        producer.send(new TranscriptProducedEvent(event.audioId(), result.transcript()));
    }
}
