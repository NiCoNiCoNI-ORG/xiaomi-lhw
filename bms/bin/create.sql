create table Battery_Rule
(
    id bigint unsigned auto_increment comment '主键'
        primary key,
    rid bigint not null comment '规则编号',
    rule_name varchar(255) not null comment '报警名称',
    battery_type varchar(255) not null comment '电池类别',
    min_value decimal(10,2) null comment '最小值（含）',
    max_value decimal(10,2) null comment '最大值（含）',
    alert_level tinyint not null comment '报警等级（0~4）'
)
    comment '电池规则表' charset=utf8mb4;

create table battery_warn
(
    id bigint unsigned auto_increment comment '主键'
        primary key,
    wid bigint unsigned not null comment '警报编号',
    rule_num bigint not null comment '规则编号',
    rule_name varchar(255) not null comment '报警名称',
    alert_level tinyint default 0 not null comment '报警等级（0~4）',
    alerted tinyint default 0 null comment '是否报警',
    constraint wid
        unique (wid)
)
    comment '电池告警表' charset=utf8mb4;

create table signal_info
(
    id bigint unsigned auto_increment comment '主键'
        primary key,
    car_id bigint unsigned not null comment '车辆ID',
    battery_capacity decimal(10,2) null comment '电池容量',
    Mx decimal(10,2) null comment '最高电压',
    Mi decimal(10,2) null comment '最低电压',
    Ix decimal(10,2) null comment '最高电流',
    Ii decimal(10,2) null comment '最低电流',
    upload_time datetime default CURRENT_TIMESTAMP null comment '上传时间'
)
    comment '信号记录表' charset=utf8mb4;

create index idx_car_id
	on signal_info (car_id);

create table vehicle_battery_info
(
    id bigint unsigned auto_increment comment '主键'
        primary key,
    vid varchar(16) not null comment '车辆识别码',
    cid bigint unsigned default 0 null comment '车架号',
    car_total_mileage bigint unsigned default 0 not null comment '车辆总里程数',
    battery_id bigint unsigned null comment '电池编号',
    battery_name varchar(255) null comment '电池名称',
    battery_type tinyint not null comment '电池类别',
    battery_total_mileage bigint default 0 not null comment '电池总里程数',
    battery_status tinyint default 100 not null comment '电池状态',
    Mx decimal(10,2) null comment '最高电压',
    Mi decimal(10,2) null comment '最低电压',
    Ix decimal(10,2) null comment '最高电流',
    Ii decimal(10,2) null comment '最低电流',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint vid
        unique (vid)
)
    comment '车辆与电池综合信息表' charset=utf8mb4;

create index idx_battery_id
	on vehicle_battery_info (battery_id);

create index idx_cid
	on vehicle_battery_info (cid);

