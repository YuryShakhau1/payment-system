--liquibase formatted sql

--changeset yury_shakhau:202608040003_add_trgm_extension
CREATE EXTENSION IF NOT EXISTS pg_trgm;

--changeset yury_shakhau:202608040003_add_trgm_idx_name runInTransaction:false
CREATE INDEX CONCURRENTLY idx_products_name_trgm ON products USING GIN (name gin_trgm_ops);

--changeset yury_shakhau:202608040003_add_trgm_idx_desc runInTransaction:false
CREATE INDEX CONCURRENTLY idx_products_desc_trgm ON products USING GIN (description gin_trgm_ops);
