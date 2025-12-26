create table enrichment_result (
    id uuid primary key,
    audio_id uuid not null,
    summary text,
    tasks text,
    raw_llm_response text,
    created_at TIMESTAMP not null default now(),
    updated_at TIMESTAMP not null default now()
);

DROP TRIGGER IF EXISTS enrichment_result_set_updated_at ON enrichment_result;

CREATE TRIGGER enrichment_result_set_updated_at
    BEFORE UPDATE ON enrichment_result
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();