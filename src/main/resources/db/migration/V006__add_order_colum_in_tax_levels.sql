ALTER TABLE income_tax_level
    ADD column "order" INT default 1;

ALTER TABLE solidarity_contribution_tax_level
    ADD column "order" INT default 1;

-- income tax level "order"
UPDATE income_tax_level
SET "order" =1
where type = 'FIRST_10K';

UPDATE income_tax_level
SET "order" =2
where type = 'SECOND_10K';

UPDATE income_tax_level
SET "order" =3
where type = 'THIRD_10K';

UPDATE income_tax_level
SET "order" =4
where type = 'FOURTH_10K';

UPDATE income_tax_level
SET "order" =5
where type = 'EXCESS';

-- solidarity tax level "order"
UPDATE solidarity_contribution_tax_level
SET "order" =1
where type = 'FIRST_12K';

UPDATE solidarity_contribution_tax_level
SET "order" =2
where type = 'SECOND_8K';

UPDATE solidarity_contribution_tax_level
SET "order" =3
where type = 'THIRD_10K';

UPDATE solidarity_contribution_tax_level
SET "order" =4
where type = 'FOURTH_10K';

UPDATE solidarity_contribution_tax_level
SET "order" =5
where type = 'FIFTH_25K';

UPDATE solidarity_contribution_tax_level
SET "order" =6
where type = 'SIXTH_155K';

UPDATE solidarity_contribution_tax_level
SET "order" =7
where type = 'EXCESS';
