package com.myorg.audiodemo.upload;

import com.myorg.audiodemo.upload.config.MinioProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MinioProperties.class)
public class UploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }
}
