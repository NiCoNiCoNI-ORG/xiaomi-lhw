package com.xiaom.bms.Service.impl;

import com.xiaom.bms.Service.SignalInfoScanService;
import com.xiaom.bms.model.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SignalInfoScanServiceImpl implements SignalInfoScanService {

    @Autowired
    private RedisTemplate<String, BaseModel> redisTemplate;

    @Scheduled(fixedRate = 60000 * 5) // 每5分钟执行一次
    @Override
    public void scanSignalInfo() {
        String pattern = "Signal_info:*";
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null) {
            for (String key : keys) {
                Object value = redisTemplate.opsForValue().get(key);

                System.out.println("Key: " + key + ", Value: " + value);
            }
        }
    }
}