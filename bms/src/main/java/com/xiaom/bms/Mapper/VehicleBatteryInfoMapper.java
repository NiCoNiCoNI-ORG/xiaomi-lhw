package com.xiaom.bms.Mapper;


import com.xiaom.bms.model.VehicleBatteryInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VehicleBatteryInfoMapper {
    @Insert("INSERT INTO vehicle_battery_info(vid, cid, car_total_mileage, battery_id, battery_name, battery_type, battery_total_mileage, battery_status, Mx, Mi, Ix, Ii, create_time, update_time) VALUES(#{vid}, #{cid}, #{carTotalMileage}, #{batteryId}, #{batteryName}, #{batteryType}, #{batteryTotalMileage}, #{batteryStatus}, #{Mx}, #{Mi}, #{Ix}, #{Ii}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(VehicleBatteryInfo vehicleBatteryInfo);

    @Update("UPDATE vehicle_battery_info SET vid=#{vid}, cid=#{cid}, car_total_mileage=#{carTotalMileage}, battery_id=#{batteryId}, battery_name=#{batteryName}, battery_type=#{batteryType}, battery_total_mileage=#{batteryTotalMileage}, battery_status=#{batteryStatus}, Mx=#{Mx}, Mi=#{Mi}, Ix=#{Ix}, Ii=#{Ii}, update_time=NOW() WHERE id=#{id}")
    int update(VehicleBatteryInfo vehicleBatteryInfo);

    @Delete("DELETE FROM vehicle_battery_info WHERE id=#{id}")
    int deleteById(String vid);

    @Select("SELECT * FROM vehicle_battery_info WHERE id=#{id}")
    VehicleBatteryInfo selectById(String id);

    @Select("SELECT * FROM vehicle_battery_info")
    List<VehicleBatteryInfo> selectAll();
}