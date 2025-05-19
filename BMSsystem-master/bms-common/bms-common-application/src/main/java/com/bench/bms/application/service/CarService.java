package com.bench.bms.application.service;

import com.bench.bms.application.model.CarDto;

import java.util.List;

/**
 * @Author bench
 * @Date 2024/06/20 19:01
 **/
public interface CarService {

    CarDto addCar(CarDto carDto);

    void remove(String vid);

    void modifyCar(CarDto carDto);

    CarDto getCar(String vid);

    List<CarDto> listCars();

}
