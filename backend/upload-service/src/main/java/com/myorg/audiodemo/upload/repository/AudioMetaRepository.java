package com.myorg.audiodemo.upload.repository;

import com.myorg.audiodemo.upload.entity.AudioMeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioMetaRepository extends JpaRepository<AudioMeta, Long> {
}
