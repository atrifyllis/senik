CREATE TABLE persisted_event
(
    id               UUID                 NOT NULL PRIMARY KEY,
    payload          JSON,
    occurred_on      TIMESTAMP WITHOUT TIME ZONE,
    dispatched_on    TIMESTAMP WITHOUT TIME ZONE,
    aggregate_id     VARCHAR(255),
    aggregate_type   VARCHAR(100),
    optlock          BIGINT  default 0,
    created_by       VARCHAR(255),
    created          TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by VARCHAR(255),
    last_modified    TIMESTAMP WITHOUT TIME ZONE,
    enabled          BOOLEAN default true not null
);
