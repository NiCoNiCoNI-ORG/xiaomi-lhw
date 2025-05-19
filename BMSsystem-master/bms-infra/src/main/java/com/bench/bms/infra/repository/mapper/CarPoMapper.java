package com.bench.bms.infra.repository.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bench.bms.infra.repository.entity.CarPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/17 20:22
 **/

@Mapper
public interface CarPoMapper extends BaseMapper<CarPo> {

    CarPo selectByCarId(Long carId);

    List<CarPo> listAllCar();

    Integer deleteByVid(String vid);

    Integer  updateCarByVid(CarPo carPo);
}
