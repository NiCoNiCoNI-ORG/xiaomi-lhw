package com.xiaom.bms.Mapper;


import com.xiaom.bms.model.SignalInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SignalInfoMapper {
    @Insert("INSERT INTO signal_info(car_id, battery_capacity, Mx, Mi, Ix, Ii, upload_time) VALUES(#{carId}, #{batteryCapacity}, #{Mx}, #{Mi}, #{Ix}, #{Ii}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SignalInfo signalInfo);

    @Update("UPDATE signal_info SET car_id=#{carId}, battery_capacity=#{batteryCapacity}, Mx=#{Mx}, Mi=#{Mi}, Ix=#{Ix}, Ii=#{Ii}, upload_time=NOW() WHERE id=#{id}")
    int update(SignalInfo signalInfo);

    @Delete("DELETE FROM signal_info WHERE id=#{id}")
    int deleteById(Long id);

    @Select("SELECT * FROM signal_info WHERE id=#{id}")
    SignalInfo selectById(Long id);

    @Select("SELECT * FROM signal_info")
    List<SignalInfo> selectAll();
}