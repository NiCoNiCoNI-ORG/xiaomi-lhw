package com.xiaom.bms.Controller;

import com.xiaom.bms.Exception.GlobalException;
import com.xiaom.bms.Service.VehicleBatteryInfoService;
import com.xiaom.bms.common.ResponseResult;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.VehicleBatteryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleBatteryInfoController {

    @Autowired
    private VehicleBatteryInfoService vehicleBatteryInfoService;

    @PostMapping("/add")
    public ResponseResult<Integer> create(@RequestBody VehicleBatteryInfo vehicleBatteryInfo) {
        try {
            int result = vehicleBatteryInfoService.createVehicleBatteryInfo(vehicleBatteryInfo);
            return ResponseResult.success(result);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseResult<VehicleBatteryInfo> get(@PathVariable String id) {
        try {
            VehicleBatteryInfo info = vehicleBatteryInfoService.getVehicleBatteryInfoById(id);
            if (info == null) {
                return ResponseResult.fail(ResponseResultType.NOT_FOUND, "Vehicle Battery Info not found");
            }
            return ResponseResult.success(info);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseResult<Integer> update(@PathVariable Long id, @RequestBody VehicleBatteryInfo vehicleBatteryInfo) {
        try {
            vehicleBatteryInfo.setId(id);
            int result = vehicleBatteryInfoService.updateVehicleBatteryInfo(vehicleBatteryInfo);
            return ResponseResult.success(result);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Void> delete(@PathVariable String vid) {
        try {
            vehicleBatteryInfoService.deleteVehicleBatteryInfo(vid);
            return ResponseResult.success();
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseResult<List<VehicleBatteryInfo>> getAll() {
        try {
            List<VehicleBatteryInfo> infos = vehicleBatteryInfoService.getAllVehicleBatteryInfos();
            return ResponseResult.success(infos);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
