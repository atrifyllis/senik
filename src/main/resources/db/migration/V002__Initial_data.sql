insert into efka_class (id,
                        type,
                        main_pension_amount, health_care_money_amount, health_care_kind_amount, unemployment_amount,
                        currency_main, currency_health_1, currency_health_2, currency_unemployment,
                        optlock, created_by, created, last_modified_by, last_modified, enabled)
values ('d55ec320-c0fe-4222-808e-3b52d9087061',
        'FIRST',
        155, 5, 50, 10,
        'EUR', 'EUR', 'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('d55ec320-c0fe-4222-808e-3b52d9087062',
        'SECOND',
        186, 6, 60, 10,
        'EUR', 'EUR', 'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('d55ec320-c0fe-4222-808e-3b52d9087063',
        'THIRD',
        155, 5, 50, 10,
        'EUR', 'EUR', 'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('d55ec320-c0fe-4222-808e-3b52d9087064',
        'FOURTH',
        155, 5, 50, 10,
        'EUR', 'EUR', 'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('d55ec320-c0fe-4222-808e-3b52d9087065',
        'FIFTH',
        155, 5, 50, 10,
        'EUR', 'EUR', 'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('d55ec320-c0fe-4222-808e-3b52d9087066',
        'SIXTH',
        155, 5, 50, 10,
        'EUR', 'EUR', 'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('d55ec320-c0fe-4222-808e-3b52d9087067',
        'NEW',
        155, 5, 50, 10,
        'EUR', 'EUR', 'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true);


insert into eteaep_class (id,
                          type,
                          auxiliary_pension_amount, lump_sum_amount,
                          currency_aux, currency_lump,
                          optlock, created_by, created, last_modified_by, last_modified, enabled)
values ('14d0b02a-2898-4c7b-8519-3bf163f8f931',
        'FIRST',
        42, 26,
        'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('14d0b02a-2898-4c7b-8519-3bf163f8f932',
        'SECOND',
        51, 31,
        'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('14d0b02a-2898-4c7b-8519-3bf163f8f933',
        'THIRD',
        61, 37,
        'EUR', 'EUR',
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true);


insert into income_tax_level (id,
                              type,
                              level_limit_amount, currency_level_limit,
                              level_factor,
                              optlock, created_by, created, last_modified_by, last_modified, enabled)
values ('cad9ad77-aecf-437c-9eee-ede330cd8371',
        'FIRST_10K',
        10000, 'EUR',
        0.09,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('cad9ad77-aecf-437c-9eee-ede330cd8372',
        'SECOND_10K',
        10000, 'EUR',
        0.22,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('cad9ad77-aecf-437c-9eee-ede330cd8373',
        'THIRD_10K',
        10000, 'EUR',
        0.28,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('cad9ad77-aecf-437c-9eee-ede330cd8374',
        'FOURTH_10K',
        10000, 'EUR',
        0.36,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('cad9ad77-aecf-437c-9eee-ede330cd8375',
        'EXCESS',
        1000000000, 'EUR',
        0.44,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true);


insert into solidarity_contribution_tax_level (id,
                                               type,
                                               level_limit_amount, currency_level_limit,
                                               level_factor,
                                               optlock, created_by, created, last_modified_by, last_modified, enabled)
values ('024e92a3-d3a0-464c-ba6e-1cee72e25f21',
        'FIRST_12K',
        12000, 'EUR',
        0,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('024e92a3-d3a0-464c-ba6e-1cee72e25f22',
        'SECOND_8K',
        10000, 'EUR',
        0.022,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('024e92a3-d3a0-464c-ba6e-1cee72e25f23',
        'THIRD_10K',
        10000, 'EUR',
        0.05,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('024e92a3-d3a0-464c-ba6e-1cee72e25f24',
        'FOURTH_10K',
        10000, 'EUR',
        0.065,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('024e92a3-d3a0-464c-ba6e-1cee72e25f25',
        'FIFTH_25K',
        25000, 'EUR',
        0.075,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('024e92a3-d3a0-464c-ba6e-1cee72e25f26',
        'SIXTH_155K',
        155000, 'EUR',
        0.09,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true),

       ('024e92a3-d3a0-464c-ba6e-1cee72e25f27',
        'EXCESS',
        1000000000, 'EUR',
        0.10,
        0, 'system', timezone('utc', now()), 'system', timezone('utc', now()), true);
