package com.bench.bms.api.model.req;

import com.bench.bms.common.model.ValidGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/26 10:34
 **/

@Data
public class RuleReq {
    @NotNull(message = "规则编号id不能为空", groups = {ValidGroup.RemoveGroup.class, ValidGroup.UpdateGroup.class, ValidGroup.SearchGroup.class})
    private Long id;
    @NotNull(message = "预警编号id不能为空", groups = {ValidGroup.InsertGroup.class, ValidGroup.UpdateGroup.class})
    private Integer warnId;
    @NotBlank(message = "预警名称不能为空", groups = {ValidGroup.InsertGroup.class, ValidGroup.UpdateGroup.class})
    private String warnName;
    @NotBlank(message = "电池类型不能为空", groups = {ValidGroup.InsertGroup.class, ValidGroup.UpdateGroup.class})
    private String batteryType;
    @NotBlank(message = "规则信息不能为空", groups = {ValidGroup.InsertGroup.class, ValidGroup.UpdateGroup.class})
    private String warnRule;
}
