package com.myorg.audiomemo.stt.config.kafka;

import com.myorg.audiomemo.stt.dto.AudioUploadedEvent;
import com.myorg.audiomemo.stt.dto.TranscriptProducedEvent;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

    @Bean
    public DefaultErrorHandler kafkaErrorHandler(KafkaTemplate<String, TranscriptProducedEvent> kafkaTemplate) {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
                kafkaTemplate,
                ((consumerRecord, e) -> new TopicPartition(consumerRecord.topic() + ".DLQ", consumerRecord.partition()))
        );

        FixedBackOff backOff = new FixedBackOff(3000L, 3);

        return new DefaultErrorHandler(recoverer, backOff);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AudioUploadedEvent> kafkaListenerContainerFactory(ConsumerFactory<String, AudioUploadedEvent> consumerFactory,
                                                                                                             DefaultErrorHandler errorHandler) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, AudioUploadedEvent>();
        factory.setConsumerFactory(consumerFactory);
        factory.setCommonErrorHandler(errorHandler);
        return factory;
    }
}
