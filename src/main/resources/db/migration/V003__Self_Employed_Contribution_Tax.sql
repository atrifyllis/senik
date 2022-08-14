CREATE TABLE self_employed_contribution
(
    id               UUID PRIMARY KEY,
    type             VARCHAR(255),
    amount           DECIMAL(12, 2),
    currency         VARCHAR(5),
    optlock          BIGINT,
    created_by       VARCHAR(255),
    created          TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by VARCHAR(255),
    last_modified    TIMESTAMP WITHOUT TIME ZONE,
    enabled          BOOLEAN
);

insert into self_employed_contribution (id, type, amount, currency,
                                        optlock, created_by, created, last_modified_by, last_modified, enabled)
values ('3d1ae738-ab91-4171-ae25-02ad491bb009', 'SINGLE_EMPLOYER_SMALL_AREA', 400, 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),
       ('7b5e30d3-63e4-41b9-9aaa-d14b912a866c', 'SINGLE_EMPLOYER_LARGE_AREA', 500, 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),
       ('22bf83d7-b3d2-4307-aacd-90967dab926a', 'SINGLE_EMPLOYER_WITH_BRANCHES', 300, 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),
       ('d84cbe2a-fe89-4af7-a81f-fec717ca6a06', 'MULTIPLE_EMPLOYERS', 650, 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),
       ('1a8cbab1-2afd-4ff5-b563-01f33b0ac075', 'MULTIPLE_EMPLOYERS_WITH_BRANCHES', 600, 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true);
