CREATE TABLE individual
(
    id                       UUID PRIMARY KEY,
    efka_class_id            UUID,
    eteaep_class_id          UUID,
    type                     VARCHAR(255),
    is_less_than_five_years  BOOLEAN NOT NULL,
    sec_type                 VARCHAR(255),
    branches                 INTEGER NOT NULL,
    annual_expenses_amount   DECIMAL,
    annual_expenses_currency VARCHAR(5),
    gross_annual_amount      DECIMAL,
    gross_annual_currency    VARCHAR(5),
    optlock                  BIGINT  NOT NULL,
    created_by               VARCHAR(255),
    created                  TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by         VARCHAR(255),
    last_modified            TIMESTAMP WITHOUT TIME ZONE,
    enabled                  BOOLEAN
);

CREATE TABLE calculated_net_income
(
    id                                      UUID PRIMARY KEY,
    individual_id                           UUID,
    insurance_amount                        DECIMAL(12, 2),
    insurance_currency                      VARCHAR(5),
    income_tax_amount                       DECIMAL(12, 2),
    income_tax_currency                     VARCHAR(5),
    solidarity_contribution_tax_amount      DECIMAL(12, 2),
    solidarity_contribution_tax_currency    VARCHAR(5),
    self_employed_contribution_tax_amount   DECIMAL(12, 2),
    self_employed_contribution_tax_currency VARCHAR(5),
    total_tax_amount                        DECIMAL(12, 2),
    total_tax_currency                      VARCHAR(5),
    net_annual_income_amount                DECIMAL(12, 2),
    net_annual_income_currency              VARCHAR(5),
    optlock                                 BIGINT,
    created_by                              VARCHAR(255),
    created                                 TIMESTAMP WITHOUT TIME ZONE,
    last_modified_by                        VARCHAR(255),
    last_modified                           TIMESTAMP WITHOUT TIME ZONE,
    enabled                                 BOOLEAN,
    CONSTRAINT fk_individual FOREIGN KEY (individual_id) REFERENCES individual (id)

);

