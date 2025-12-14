ALTER TABLE audio_meta
    ALTER COLUMN created_at SET DEFAULT now(),
ALTER COLUMN updated_at SET DEFAULT now();

UPDATE audio_meta
SET created_at = COALESCE(created_at, now()),
    updated_at = COALESCE(updated_at, now());

CREATE OR REPLACE FUNCTION set_updated_at()
RETURNS trigger AS $$
BEGIN
  NEW.updated_at := now();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS audio_meta_set_updated_at ON audio_meta;

CREATE TRIGGER audio_meta_set_updated_at
    BEFORE UPDATE ON audio_meta
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();