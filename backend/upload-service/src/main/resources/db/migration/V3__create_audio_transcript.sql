CREATE TABLE audio_transcript (
    audio_id UUID PRIMARY KEY,
    text TEXT NOT NULL,
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);

ALTER TABLE audio_transcript
    ALTER COLUMN created_at SET DEFAULT now(),
ALTER COLUMN updated_at SET DEFAULT now();

UPDATE audio_transcript
SET created_at = COALESCE(created_at, now()),
    updated_at = COALESCE(updated_at, now());

CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS trigger AS $$
BEGIN
  NEW.updated_at := now();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS audio_transcript_set_updated_at ON audio_transcript;

CREATE TRIGGER audio_transcript_set_updated_at
    BEFORE UPDATE ON audio_transcript
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();