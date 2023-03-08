CREATE TABLE processed_event
(
    event_id         uuid                 NOT NULL PRIMARY KEY,
    optlock          BIGINT  default 0,
    created_by       VARCHAR(255),
    created          TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by VARCHAR(255),
    last_modified    TIMESTAMP WITHOUT TIME ZONE,
    enabled          BOOLEAN default true not null
);
