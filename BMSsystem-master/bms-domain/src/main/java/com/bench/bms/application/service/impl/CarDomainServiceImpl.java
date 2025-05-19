package com.bench.bms.application.service.impl;

import com.bench.bms.common.exception.DomainException;
import com.bench.bms.common.exception.exceptionsenum.DomainExceptionEnum;
import com.bench.bms.domain.model.CarDo;
import com.bench.bms.domain.service.CarDomainService;
import com.bench.bms.infra.repository.CarRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


/**
 * @Author bench
 * @Date 2024/06/20 19:52
 **/

@Service
public class CarDomainServiceImpl implements CarDomainService {

    @Resource
    private CarRepository carRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CarDo saveCar(CarDo carDo){
        String carDoString = carDo.toString();
        carDo.setVid(generateVid(carDoString));
        carRepository.save(carDo);
//        throw new RuntimeException("事务回滚测试");
        return carDo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void modifyCar(CarDo carDo) {
        if (carRepository.update(carDo) == 0){
            throw new DomainException(DomainExceptionEnum.CAR_NOT_FOUND);
        }
//        carRepository.update(carDo);
    }

    @Override
    public void removeCar(String vid) {
        if (carRepository.remove(vid) == 0){
            throw new DomainException(DomainExceptionEnum.CAR_NOT_FOUND);
        }
//        carRepository.remove(vid);
    }

    @Override
    public String generateVid(String input){
        long timeStamp = System.currentTimeMillis();
        String timestampStr = Long.toString(timeStamp);
        String vidHash = DigestUtils.md5DigestAsHex((input + timestampStr).getBytes()).substring(0, 3);
        return vidHash + timestampStr;
    }
}
