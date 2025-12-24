package com.myorg.audiomemo.stt.repository;

import com.myorg.audiomemo.stt.entity.AudioTranscript;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AudioTranscriptRepository extends JpaRepository<AudioTranscript, UUID> {
}
