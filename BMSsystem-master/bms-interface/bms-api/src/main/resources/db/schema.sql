
DROP TABLE IF EXISTS `RULE`;
DROP TABLE IF EXISTS `CAR`;
DROP TABLE IF EXISTS `WARN`;

CREATE TABLE RULE (
      id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
      warn_id VARCHAR(255) NOT NULL COMMENT '规则编号',
      warn_name VARCHAR(255) NOT NULL COMMENT '规则名称',
      battery_type VARCHAR(255) NOT NULL COMMENT '电池类型',
      warn_rule VARCHAR(255) NOT NULL COMMENT '预警规则',
      create_time BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
      create_user VARCHAR(255) NOT NULL COMMENT '创建者',
      update_time BIGINT UNSIGNED NOT NULL COMMENT '修改时间',
      update_user VARCHAR(255) NOT NULL COMMENT '修改者',
      INDEX idx_warn_id (warn_id),
      INDEX idx_battery_type (battery_type),
      INDEX idx_warn_rule (warn_rule)  -- 如果需要索引，使用前缀索引
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预警规则表';

INSERT INTO RULE (warn_id, warn_name, battery_type, warn_rule)
VALUES
    ('1', '电压差报警', '三元电池', '5: 0, 3: 1, 1: 2, 0.6: 3; 0.2: 4'),
    ('1', '电压差报警', '铁锂电池', '2: 0, 1: 1, 0.7: 2, 0.4: 3; 0.2: 4'),
    ('2', '电流差报警', '三元电池', '3: 0, 1: 1, 0.2: 2'),
    ('2', '电流差报警', '铁锂电池', '1: 0, 0.5: 1, 0.2: 2');

CREATE TABLE CAR (
     id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
     vid VARCHAR(16) NOT NULL COMMENT '车辆识别码，每辆车唯一，16位随机字符串',
     car_id BIGINT UNSIGNED NOT NULL COMMENT '车架编号',
     battery_type VARCHAR(255) NOT NULL COMMENT '电池类型',
     total_mileage BIGINT UNSIGNED NOT NULL COMMENT '总里程(km)',
     battery_status INT UNSIGNED NOT NULL COMMENT '电池健康状态(%)',
     create_time BIGINT UNSIGNED NOT NULL COMMENT '创建时间',
     create_user VARCHAR(255) NOT NULL COMMENT '创建者',
     update_time BIGINT UNSIGNED NOT NULL COMMENT '修改时间',
     update_user VARCHAR(255) NOT NULL COMMENT '修改者',
     UNIQUE INDEX idx_vid (vid),
     INDEX idx_car_id (car_id),
     INDEX idx_battery_type (battery_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车辆信息表';

INSERT INTO CAR (vid, car_id, battery_type, total_mileage, battery_status)
VALUES
    ('1a2b3c4d5e6f7g8h', 1, '三元电池', 100, 100),
    ('2b3c4d5e6f7g8h9i', 2, '铁锂电池', 600, 95),
    ('3c4d5e6f7g8h9i0j', 3, '三元电池', 300, 98);

CREATE TABLE WARN (
       id bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
       car_id bigint NOT NULL COMMENT '车辆ID',
       warn_name varchar(255) NOT NULL COMMENT '警告名称',
       battery_type varchar(255) NOT NULL COMMENT '电池类型',
       warn_level int NOT NULL COMMENT '警告级别',
       create_time bigint UNSIGNED NOT NULL COMMENT '创建时间戳',
       create_user varchar(50) NOT NULL COMMENT '创建者',
       update_time bigint UNSIGNED NOT NULL COMMENT '修改时间戳',
       update_user varchar(50) NOT NULL COMMENT '修改者',
       PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='警告信息表，存储车辆警告信息';
