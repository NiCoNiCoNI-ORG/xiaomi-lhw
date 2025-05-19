INSERT INTO WarningRules (rule_code, rule_name, battery_type, warning_rule, create_time, create_user, update_time, update_user)
VALUES
    ('1', '电压差报警', '三元电池', '5: 0, 3: 1, 1: 2, 0.6: 3; 0.2: 4', UNIX_TIMESTAMP(), 'admin', UNIX_TIMESTAMP(), 'admin'),
    ('1', '电压差报警', '铁锂电池', '2: 0, 1: 1, 0.7: 2, 0.4: 3; 0.2: 4', UNIX_TIMESTAMP(), 'admin', UNIX_TIMESTAMP(), 'admin'),
    ('2', '电流差报警', '三元电池', '3: 0, 1: 1, 0.2: 2', UNIX_TIMESTAMP(), 'admin', UNIX_TIMESTAMP(), 'admin'),
    ('2', '电流差报警', '铁锂电池', '1: 0, 0.5: 1, 0.2: 2', UNIX_TIMESTAMP(), 'admin', UNIX_TIMESTAMP(), 'admin');
