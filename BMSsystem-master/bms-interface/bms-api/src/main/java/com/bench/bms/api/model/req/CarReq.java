package com.bench.bms.api.model.req;

import com.bench.bms.common.model.ValidGroup.InsertGroup;
import com.bench.bms.common.model.ValidGroup.UpdateGroup;
import com.bench.bms.common.model.ValidGroup.RemoveGroup;
import com.bench.bms.common.model.ValidGroup.SearchGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Author bench
 * @Date 2024/06/20 19:04
 **/

@Data
public class CarReq {

//    private
    @NotBlank(message = "汽车vid不能为空", groups = {RemoveGroup.class, UpdateGroup.class, SearchGroup.class})
    private String vid;

    @NotNull(message = "汽车id不能为空", groups = {InsertGroup.class, UpdateGroup.class})
    private Long carId;

    @NotBlank(message = "电池类型不能为空", groups = {InsertGroup.class, UpdateGroup.class})
    private String batteryType;

    @NotNull(message = "汽车总里程不能为空", groups = {InsertGroup.class, UpdateGroup.class})
    private Long totalMileage;

    @NotNull(message = "电池状态不能为空", groups = {InsertGroup.class, UpdateGroup.class})
    private Integer batteryStatus;

}
