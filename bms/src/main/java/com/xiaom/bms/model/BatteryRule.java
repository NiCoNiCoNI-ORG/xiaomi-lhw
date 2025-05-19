package com.xiaom.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@Entity
@Table(name = "Battery_Rule") // 指定表名
public class BatteryRule  extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private Long id;

    @NotNull(message = "规则编号不能为空")
    @Column(name = "rid", nullable = false)
    private Long rid;

    @JsonProperty("alertType")
    @NotBlank(message = "报警名称不能为空")
    @Size(max = 255, message = "报警名称长度不能超过255个字符")
    @Column(name = "rule_name", nullable = false, length = 255)
    private String ruleName;

    @NotBlank(message = "电池类别不能为空")
    @Size(max = 255, message = "电池类别长度不能超过255个字符")
    @Column(name = "battery_type", nullable = false, length = 255)
    private String batteryType;

    @DecimalMin(value = "-999999999.99", inclusive = true, message = "最小值必须大于等于-999999999.99")
    @DecimalMax(value = "999999999.99", inclusive = true, message = "最小值必须小于等于999999999.99")
    @Column(name = "min_value", precision = 10, scale = 2)
    private BigDecimal minValue;

    @DecimalMin(value = "-999999999.99", inclusive = true, message = "最大值必须大于等于-999999999.99")
    @DecimalMax(value = "999999999.99", inclusive = true, message = "最大值必须小于等于999999999.99")
    @Column(name = "max_value", precision = 10, scale = 2)
    private BigDecimal maxValue;


    @JsonProperty("level")
    @NotNull(message = "报警等级不能为空")
    @Min(value = 0, message = "报警等级必须大于等于0")
    @Max(value = 4, message = "报警等级必须小于等于4")
    @Column(name = "alert_level", nullable = false)
    private Byte alertLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public Byte getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(Byte alertLevel) {
        this.alertLevel = alertLevel;
    }
}
