package com.xiaom.bms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "signal_info")
public class SignalInfo extends BaseModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增主键
    private Long id;

    @NotNull(message = "车辆ID(car_id)不能为空")
    @Positive(message = "车辆ID(car_id)必须大于0")
    @Column(name = "car_id", nullable = false)
    private Long carId;

    @DecimalMin(value = "0.00", inclusive = true, message = "电池容量不能为负数")
    @Column(name = "battery_capacity", precision = 10, scale = 2, nullable = true)
    private BigDecimal batteryCapacity;

    @DecimalMin(value = "0.00", inclusive = true, message = "最高电压(Mx)不能为负数")
    @Column(name = "Mx", precision = 10, scale = 2, nullable = true)
    @JsonProperty("Mx") // 明确指定JSON字段名
    private BigDecimal Mx;

    @DecimalMin(value = "0.00", inclusive = true, message = "最低电压(Mi)不能为负数")
    @Column(name = "Mi", precision = 10, scale = 2, nullable = true)
    @JsonProperty("Mi") // 明确指定JSON字段名
    private BigDecimal Mi;

    @DecimalMin(value = "0.00", inclusive = true, message = "最高电流(Ix)不能为负数")
    @Column(name = "Ix", precision = 10, scale = 2, nullable = true)
    @JsonProperty("Ix") // 明确指定JSON字段名
    private BigDecimal Ix;

    @DecimalMin(value = "0.00", inclusive = true, message = "最低电流(Ii)不能为负数")
    @Column(name = "Ii", precision = 10, scale = 2, nullable = true)
    @JsonProperty("Ii") // 明确指定JSON字段名
    private BigDecimal Ii;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upload_time", nullable = true)
    private Date uploadTime;
}