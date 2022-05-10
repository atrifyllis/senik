CREATE TABLE efka_class
(
    id                       UUID PRIMARY KEY,
    type                     VARCHAR(255),
    main_pension_amount      DECIMAL(12, 2),
    health_care_money_amount DECIMAL(12, 2),
    health_care_kind_amount  DECIMAL(12, 2),
    unemployment_amount      DECIMAL(12, 2),
    currency_main            VARCHAR(5),
    currency_health_1        VARCHAR(5),
    currency_health_2        VARCHAR(5),
    currency_unemployment    VARCHAR(5),
    optlock                  BIGINT  default 0,
    created_by               VARCHAR(255),
    created                  TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by         VARCHAR(255),
    last_modified            TIMESTAMP WITHOUT TIME ZONE,
    enabled                  BOOLEAN default true not null
);

CREATE TABLE eteaep_class
(
    id                       UUID PRIMARY KEY,
    type                     VARCHAR(255),
    auxiliary_pension_amount DECIMAL(12, 2),
    lump_sum_amount          DECIMAL(12, 2),
    currency_aux             VARCHAR(5),
    currency_lump            VARCHAR(5),
    optlock                  BIGINT,
    created_by               VARCHAR(255),
    created                  TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by         VARCHAR(255),
    last_modified            TIMESTAMP WITHOUT TIME ZONE,
    enabled                  BOOLEAN
);

CREATE TABLE income_tax_level
(
    id                   UUID PRIMARY KEY,
    type                 VARCHAR(255),
    level_limit_amount   DECIMAL(12, 2),
    currency_level_limit VARCHAR(5),
    level_factor         DOUBLE PRECISION,
    optlock              BIGINT,
    created_by           VARCHAR(255),
    created              TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by     VARCHAR(255),
    last_modified        TIMESTAMP WITHOUT TIME ZONE,
    enabled              BOOLEAN
);

CREATE TABLE solidarity_contribution_tax_level
(
    id                   UUID PRIMARY KEY,
    type                 VARCHAR(255),
    level_limit_amount   DECIMAL(12, 2),
    currency_level_limit VARCHAR(5),
    level_factor         DOUBLE PRECISION,
    optlock              BIGINT,
    created_by           VARCHAR(255),
    created              TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by     VARCHAR(255),
    last_modified        TIMESTAMP WITHOUT TIME ZONE,
    enabled              BOOLEAN
);
