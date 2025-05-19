package com.bench.bms.api.web;

import com.bench.bms.api.converter.CarVoConverter;
import com.bench.bms.api.model.req.CarReq;
import com.bench.bms.api.model.res.CarVo;
import com.bench.bms.application.model.CarDto;
import com.bench.bms.application.service.CarService;
import com.bench.bms.common.model.BaseRes;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bench.bms.common.model.ValidGroup.InsertGroup;
import com.bench.bms.common.model.ValidGroup.UpdateGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author bench
 * @Date 2024/06/20 18:47
 **/
@Slf4j
@RestController
@RequestMapping("/api")
public class CarController {

    @Resource
    private CarService carService;

    @Resource
    private CarVoConverter carVoConverter;

    @PostMapping("/car/add")
    public BaseRes<CarVo> add(@Validated(InsertGroup.class) @RequestBody CarReq carReq){
        log.info("request add car: {}.", carReq);
        CarDto carDto = carVoConverter.toDto(carReq);
        carDto = carService.addCar(carDto);
        return BaseRes.success(carVoConverter.toVo(carDto));
    }

    @DeleteMapping("/car/{vid}")
    public BaseRes<Void> delete(@PathVariable(value = "vid") String vid){
        log.info("request delete car by vid: {}.", vid);
        carService.remove(vid);
        return BaseRes.success();
    }

    @PutMapping("/car/modify")
    public BaseRes<CarVo> modify(@Validated(UpdateGroup.class) @RequestBody CarReq carReq){
        log.info("request modify car: {}", carReq);
        CarDto carDto = carVoConverter.toDto(carReq);
        carService.modifyCar(carDto);
        return BaseRes.success(carVoConverter.toVo(carDto));
    }

    @GetMapping("/car/{vid}")
    public BaseRes<CarVo> findCar(@PathVariable(value = "vid") String vid) {
        log.info("request find car:{}. ", vid);
        CarDto carDto = carService.getCar(vid);
        log.info("request find user:{}. response", carDto);
        return BaseRes.success(carVoConverter.toVo(carDto));
    }

    @GetMapping("/car")
    public BaseRes<List<CarVo>> listAllCar() {
        log.info("request find car:{}. ");
        List<CarDto> carDtoList = carService.listCars();
        log.info("request find user:{}. response");
        List<CarVo> carVoList = carDtoList.stream()
                .map(carVoConverter::toVo)
                .collect(Collectors.toList());
        return BaseRes.success(carVoList);
    }
}
