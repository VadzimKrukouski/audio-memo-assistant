package com.myorg.audiodemo.upload.services;

import com.myorg.audiodemo.upload.dto.AudioUploadedEvent;
import com.myorg.audiodemo.upload.entity.AudioMeta;
import com.myorg.audiodemo.upload.repository.AudioMetaRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class UploadService {

    private final AudioEventProducer producer;
    private final AudioMetaRepository repository;
    private final MinioClient minioClient;

    public UploadService(AudioEventProducer producer, AudioMetaRepository repository, MinioClient minioClient) {
        this.producer = producer;
        this.repository = repository;
        this.minioClient = minioClient;
    }

    public UUID upload(MultipartFile file) throws Exception {
        UUID id = UUID.randomUUID();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket("audio")
                        .object(id.toString())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        repository.save(new AudioMeta(
                id,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize()
        ));

        producer.send(new AudioUploadedEvent(
                id,
                "audio",
                id.toString()
        ));

        return id;
    }
}
