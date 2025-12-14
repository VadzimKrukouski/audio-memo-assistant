package com.myorg.audiodemo.upload.controllers;

import com.myorg.audiodemo.upload.services.UploadService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class UploadController {

    private final Path storage = Paths.get("uploads");
    private final UploadService uploadService;

    public UploadController(UploadService uploadService) throws IOException {
        Files.createDirectories(storage);
        this.uploadService = uploadService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UUID> upload(@RequestPart("file") MultipartFile file) {
        try {
            return new ResponseEntity<>(uploadService.upload(file), HttpStatusCode.valueOf(201));
        } catch (Exception e) {
           return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id) {
        Path file = storage.resolve(id);
        if (!Files.exists(file)) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
