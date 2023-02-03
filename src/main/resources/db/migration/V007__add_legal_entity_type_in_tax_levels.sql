ALTER TABLE income_tax_level
    ADD column legal_entity_type VARCHAR(255);

ALTER TABLE solidarity_contribution_tax_level
    ADD legal_entity_type VARCHAR(255);

UPDATE income_tax_level
SET legal_entity_type = 'INDIVIDUAL';

UPDATE solidarity_contribution_tax_level
SET legal_entity_type ='INDIVIDUAL';
