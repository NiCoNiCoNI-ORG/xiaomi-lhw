package com.xiaom.bms.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
@Getter
@Setter
@Data
@Entity
@Table(name = "battery_warn")
public class BatteryWarn extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private Long id;

    @NotNull(message = "警报编号(wid)不能为空")
    @Positive(message = "警报编号(wid)必须大于0")
    @Column(unique = true)
    private Long wid;

    @NotNull(message = "规则编号(rule_num)不能为空")
    @Positive(message = "规则编号(rule_num)必须大于0")
    @Column(name = "rule_num", nullable = false)
    private Long ruleNum;

    @NotBlank(message = "报警名称(rule_name)不能为空")
    @Size(max = 255, message = "报警名称不能超过255个字符")
    @Column(name = "rule_name", nullable = false, length = 255)
    private String ruleName;

    @NotNull(message = "报警等级(alert_level)不能为空")
    @Min(value = 0, message = "报警等级(alert_level)不能小于0")
    @Max(value = 4, message = "报警等级(alert_level)不能大于4")
    @Column(name = "alert_level", nullable = false, columnDefinition = "tinyint default 0")
    private Byte alertLevel;

    @Column(nullable = true, columnDefinition = "tinyint default 0")
    private Byte alerted; // 可为空

}



