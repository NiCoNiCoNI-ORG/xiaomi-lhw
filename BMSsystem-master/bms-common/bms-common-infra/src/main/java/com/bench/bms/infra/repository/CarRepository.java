package com.bench.bms.infra.repository;

import com.bench.bms.domain.model.CarDo;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/20 19:36
 **/
public interface CarRepository {

    CarDo findByVid(String vid);

    void save(CarDo carDo);

    Integer remove(String vid);

    Integer update(CarDo carDo);

    List<CarDo> list();

}
