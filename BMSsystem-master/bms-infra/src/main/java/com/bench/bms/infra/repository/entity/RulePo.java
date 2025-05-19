package com.bench.bms.infra.repository.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @Author bench
 * @Date 2024/06/17 20:04
 **/

@Data
@TableName("rule")
public class RulePo {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer warnId;
    private String warnName;
    private String batteryType;
    private String warnRule;

    @TableField(fill = FieldFill.INSERT)
    private Long createTime;

    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUser;

}
