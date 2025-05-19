package com.xiaom.bms.Service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.xiaom.bms.Exception.GlobalException;
import com.xiaom.bms.Mapper.SignalInfoMapper;
import com.xiaom.bms.Service.SignalInfoService;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.BaseModel;
import com.xiaom.bms.model.SignalInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//先查询redis

@Service
@EnableScheduling
public class SignalInfoServiceImpl implements SignalInfoService {

    @Autowired
    private SignalInfoMapper signalInfoMapper;

    @Resource
    private RedisTemplate<String, BaseModel> redisTemplate;

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String SIGNAL_INFO_CACHE_KEY = "signal:info:";

    @Scheduled(fixedDelay = 60000)
    public void syncRedisToMySQL() {
        Set<String> keys = redisTemplate.keys(SIGNAL_INFO_CACHE_KEY + "*");
        if (keys != null) {
            for (String key : keys) {
                SignalInfo signalInfo = (SignalInfo) redisTemplate.opsForValue().get(key);
                if (signalInfo != null) {
                    SignalInfo existing = signalInfoMapper.selectById(signalInfo.getId());
                    if (existing == null) {
                        signalInfoMapper.insert(signalInfo);
                    } else {
                        signalInfoMapper.update(signalInfo);
                    }
                }
            }
        }
    }

    public void validateSignalInfo(SignalInfo signalInfo) {
        if (signalInfo == null) {
            throw new GlobalException(ResponseResultType.BAD_REQUEST, "信号信息不能为空");
        }
        if (signalInfo.getCarId() == null || signalInfo.getCarId() <= 0) {
            throw new IllegalArgumentException("车辆ID(car_id)必须大于0");
        }
        if (signalInfo.getBatteryCapacity() != null && signalInfo.getBatteryCapacity().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("电池容量(battery_capacity)不能为负数");
        }
        if (signalInfo.getMx() != null && signalInfo.getMx().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("最高电压(Mx)不能为负数");
        }
        if (signalInfo.getMi() != null && signalInfo.getMi().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("最低电压(Mi)不能为负数");
        }
        if (signalInfo.getIx() != null && signalInfo.getIx().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("最高电流(Ix)不能为负数");
        }
        if (signalInfo.getIi() != null && signalInfo.getIi().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("最低电流(Ii)不能为负数");
        }
    }

    @Override
    public int createSignalInfo(SignalInfo signalInfo) {
        validateSignalInfo(signalInfo);
        String cacheKey = SIGNAL_INFO_CACHE_KEY + signalInfo.getId();
        redisTemplate.opsForValue().set(cacheKey, signalInfo, 1, TimeUnit.HOURS);
        return 1;
    }

    @Override
    @Transactional
    public int updateSignalInfo(SignalInfo signalInfo) {
        validateSignalInfo(signalInfo);
        int result = signalInfoMapper.update(signalInfo);
        if (result > 0) {
            String cacheKey = SIGNAL_INFO_CACHE_KEY + signalInfo.getId();
            redisTemplate.delete(cacheKey);
            redisTemplate.opsForValue().set(cacheKey, signalInfo, 1, TimeUnit.HOURS);
        }
        return result;
    }

    @Override
    @Transactional
    public int deleteSignalInfo(Long id) {
        int result = signalInfoMapper.deleteById(id);
        if (result > 0) {
            String cacheKey = SIGNAL_INFO_CACHE_KEY + id;
            redisTemplate.delete(cacheKey);
        }
        return result;
    }

    @Override
    public SignalInfo getSignalInfoById(Long id) {
        String cacheKey = SIGNAL_INFO_CACHE_KEY + id;
        SignalInfo signalInfo = (SignalInfo) redisTemplate.opsForValue().get(cacheKey);
        if (signalInfo == null) {
            signalInfo = signalInfoMapper.selectById(id);
            if (signalInfo != null) {
                redisTemplate.opsForValue().set(cacheKey, signalInfo, 5, TimeUnit.MINUTES);
            }
        }
        return signalInfo;
    }

    @Override
    public List<SignalInfo> getAllSignalInfos() {
        return signalInfoMapper.selectAll();
    }

}
