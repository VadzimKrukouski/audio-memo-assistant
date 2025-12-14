CREATE TABLE audio_meta (
    id UUID PRIMARY KEY,
    original_file_name VARCHAR(255) not null,
    content_type VARCHAR(100) not null,
    size BIGINT not null,
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);

