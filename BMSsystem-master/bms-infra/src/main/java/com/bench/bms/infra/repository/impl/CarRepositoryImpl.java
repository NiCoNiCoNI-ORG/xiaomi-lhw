package com.bench.bms.infra.repository.impl;

import com.bench.bms.domain.model.CarDo;
import com.bench.bms.infra.repository.CarRepository;
import com.bench.bms.infra.repository.converter.CarPoConverter;
import com.bench.bms.infra.repository.entity.CarPo;
import com.bench.bms.infra.repository.mapper.CarPoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author bench
 * @Date 2024/06/20 19:40
 **/

@Service
public class CarRepositoryImpl implements CarRepository {

    @Resource
    private CarPoMapper carPoMapper;

    @Resource
    private CarPoConverter carPoConverter;

    @Override
    public CarDo findByVid(String vid) {
        CarPo carPo = carPoMapper.selectById(vid);
        return carPoConverter.toDo(carPo);
    }

    @Override
    public void save(CarDo carDo) {
        CarPo carPo = carPoConverter.toPo(carDo);
        System.out.println(carPo);
        carPoMapper.insert(carPo);
    }

    @Override
    public Integer remove(String vid) {
        return carPoMapper.deleteByVid(vid);
    }

    @Override
    public Integer update(CarDo carDo) {
        CarPo carPo = carPoConverter.toPo(carDo);
//        carPoMapper.
        return carPoMapper.updateCarByVid(carPo);
    }

    @Override
    public List<CarDo> list() {
        List<CarPo> carPoList = carPoMapper.listAllCar();
        List<CarDo> carDoList = carPoList.stream()
                .map(carPoConverter::toDo)
                .collect(Collectors.toList());
        return carDoList;
    }
}
