package com.bench.bms.infra.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author bench
 * @Date 2024/06/17 15:22
 **/

@Data
@TableName("warn")
public class WarnPo {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long carId;
    private String batteryType;
    private String warnName;
    private Integer warnLevel;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;
}
