package com.xiaom.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xiaom.bms.common.BATTERY_NAME;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Data
@Entity
@Table(name = "vehicle_battery_info")
public class VehicleBatteryInfo extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private Long id;

    @NotBlank(message = "车辆识别码(vid)不能为空")
    @Size(max = 16, message = "车辆识别码(vid)长度不能超过16个字符")
    @Column(name = "vid", unique = true, nullable = false)
    private String vid;

    @Column(name = "cid", nullable = true)
    private Long cid;

    @JsonProperty("car_total_mileage")
    @NotNull(message = "车辆总里程数(car_total_mileage)不能为空")
    @Min(value = 0, message = "车辆总里程数不能小于0")
    @Column(name = "car_total_mileage", nullable = false)
    private Long carTotalMileage;

    @JsonProperty("battery_id")
    @Column(name = "battery_id", nullable = true)
    private Long batteryId;
    @JsonProperty("battery_name")
    @Column(name = "battery_name", nullable = true)
    private String batteryName;
    @JsonProperty("battery_type")
    @NotNull(message = "电池类别(battery_type)不能为空")
    @Min(value = 0, message = "电池类别(battery_type)不能小于0")
    @Max(value = 255, message = "电池类别(battery_type)不能大于255")
    @Column(name = "battery_type", nullable = false, columnDefinition = "tinyint default 0")
    private Byte batteryType;

    @JsonProperty("battery_total_mileage")
    @NotNull(message = "电池总里程数(battery_total_mileage)不能为空")
    @Min(value = 0, message = "电池总里程数不能小于0")
    @Column(name = "battery_total_mileage", nullable = false)
    private Long batteryTotalMileage;

    @JsonProperty("battery_status")
    @NotNull(message = "电池状态(battery_status)不能为空")
    @Min(value = 1, message = "电池状态(battery_status)不能小于1")
    @Max(value = 3, message = "电池状态(battery_status)不能大于3")
    @Column(name = "battery_status", nullable = false)
    private Byte batteryStatus;

    @JsonProperty("Mx")
    @DecimalMin(value = "0.00", inclusive = true, message = "最高电压(Mx)不能为负数")
    @Column(name = "mx", nullable = true)
    private BigDecimal Mx;

    @JsonProperty("Mi")
    @DecimalMin(value = "0.00", inclusive = true, message = "最低电压(Mi)不能为负数")
    @Column(name = "mi", nullable = true)
    private BigDecimal Mi;
    @JsonProperty("Ix")
    @DecimalMin(value = "0.00", inclusive = true, message = "最高电流(Ix)不能为负数")
    @Column(name = "Ix", nullable = true)
    private BigDecimal Ix;
    @JsonProperty("Ii")
    @DecimalMin(value = "0.00", inclusive = true, message = "最低电流(Ii)不能为负数")
    @Column(name = "Ii", nullable = true)
    private BigDecimal Ii;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date updateTime;


}