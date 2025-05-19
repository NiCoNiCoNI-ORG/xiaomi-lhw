package com.xiaom.bms.Service.impl;

import com.xiaom.bms.Mapper.VehicleBatteryInfoMapper;
import com.xiaom.bms.Service.VehicleBatteryInfoService;
import com.xiaom.bms.model.BaseModel;
import com.xiaom.bms.model.VehicleBatteryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


//定时更新状态

@Service
public class VehicleBatteryInfoServiceImpl implements VehicleBatteryInfoService {
    @Autowired
    private VehicleBatteryInfoMapper vehicleBatteryInfoMapper;
    @Resource
    private RedisTemplate<String, BaseModel> redisTemplate;
    @Override
    @Transactional
    public int createVehicleBatteryInfo(VehicleBatteryInfo vehicleBatteryInfo) {
//        validateVehicleBatteryInfo(vehicleBatteryInfo);
        saveToRedis(vehicleBatteryInfo);
        return vehicleBatteryInfoMapper.insert(vehicleBatteryInfo);
    }


    @Override
    @Transactional
    public int updateVehicleBatteryInfo(VehicleBatteryInfo vehicleBatteryInfo) {
        validateVehicleBatteryInfo(vehicleBatteryInfo);
        saveToRedis(vehicleBatteryInfo);
        return vehicleBatteryInfoMapper.update(vehicleBatteryInfo);
    }

    @Override
    @Transactional
    public int deleteVehicleBatteryInfo(String vid) {
        return vehicleBatteryInfoMapper.deleteById(vid);
    }

    @Override
    public VehicleBatteryInfo getVehicleBatteryInfoById(String id) {

//        先拿redis中的
        String key = "vehicle:battery:" + id;
        VehicleBatteryInfo batteryInfo = (VehicleBatteryInfo) redisTemplate.opsForValue().get(key);
                if (null==batteryInfo ) {
            // 如果 Redis 中没有找到，从 MySQL 获取
            batteryInfo = vehicleBatteryInfoMapper.selectById(id);

            if (null!=batteryInfo  ) {
                // 将从 MySQL 获取的数据保存到 Redis
                saveToRedis(batteryInfo);
            }else{
                return null;
            }
        }
        return  batteryInfo;
    }

    public void saveToRedis(VehicleBatteryInfo vehicleBatteryInfo) {
        String key = "vehicle:battery:" + vehicleBatteryInfo.getVid();
//        redisTemplate.opsForValue().set(key, vehicleBatteryInfo);
        redisTemplate.opsForValue().set(key, vehicleBatteryInfo, 5, TimeUnit.MINUTES);
    }

    @Override
    public List<VehicleBatteryInfo> getAllVehicleBatteryInfos() {
        List<VehicleBatteryInfo> list=vehicleBatteryInfoMapper.selectAll();
        for(VehicleBatteryInfo VehicleBatteryInfo  :list){
            saveToRedis(VehicleBatteryInfo);
        }
        return list;
    }

    public void validateVehicleBatteryInfo(VehicleBatteryInfo info) {
        if (info == null) {
            throw new IllegalArgumentException("车辆电池信息不能为空");
        }
        if (info.getVid() == null || info.getVid().length() > 16) {
            throw new IllegalArgumentException("车辆识别码(vid)不能为空，且长度不能超过16");
        }
        if (info.getCarTotalMileage() == null || info.getCarTotalMileage() < 0) {
            throw new IllegalArgumentException("车辆总里程(car_total_mileage)不能小于0");
        }
        if (info.getBatteryType() == null || info.getBatteryType() < 0 || info.getBatteryType() > 255) {
            throw new IllegalArgumentException("电池类别(battery_type)必须在0~255之间");
        }
        if (info.getBatteryTotalMileage() == null || info.getBatteryTotalMileage() < 0) {
            throw new IllegalArgumentException("电池总里程(battery_total_mileage)不能小于0");
        }
        if (info.getBatteryStatus() == null || info.getBatteryStatus() < 0 || info.getBatteryStatus() > 255) {
            throw new IllegalArgumentException("电池状态(battery_status)必须在0~255之间");
        }
    }
}