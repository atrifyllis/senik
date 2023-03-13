CREATE TABLE persisted_event
(
    id               UUID                 NOT NULL PRIMARY KEY,
    aggregatetype    VARCHAR(255),
    aggregateid      VARCHAR(255),
    type             VARCHAR(255),
    payload          JSONB,
-- columns above are required from debezium:
-- https://debezium.io/documentation/reference/stable/transformations/outbox-event-router.html#basic-outbox-table
    occurred_on      TIMESTAMP WITHOUT TIME ZONE,
    dispatched_on    TIMESTAMP WITHOUT TIME ZONE,
    optlock          BIGINT  default 0,
    created_by       VARCHAR(255),
    created          TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by VARCHAR(255),
    last_modified    TIMESTAMP WITHOUT TIME ZONE,
    enabled          BOOLEAN default true not null
);
