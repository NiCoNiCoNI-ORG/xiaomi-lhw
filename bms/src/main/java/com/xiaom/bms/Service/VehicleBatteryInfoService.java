package com.xiaom.bms.Service;


import com.xiaom.bms.model.VehicleBatteryInfo;

import java.util.List;

public interface VehicleBatteryInfoService {
    int createVehicleBatteryInfo(VehicleBatteryInfo vehicleBatteryInfo);
    int updateVehicleBatteryInfo(VehicleBatteryInfo vehicleBatteryInfo);
    int deleteVehicleBatteryInfo(String vid);
    VehicleBatteryInfo getVehicleBatteryInfoById(String id);
    List<VehicleBatteryInfo> getAllVehicleBatteryInfos();
}