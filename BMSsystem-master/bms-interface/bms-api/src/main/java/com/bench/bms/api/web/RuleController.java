package com.bench.bms.api.web;

import com.bench.bms.api.converter.RuleVoConverter;
import com.bench.bms.api.model.req.CarReq;
import com.bench.bms.api.model.req.RuleReq;
import com.bench.bms.api.model.res.CarVo;
import com.bench.bms.api.model.res.RuleVo;
import com.bench.bms.application.model.CarDto;
import com.bench.bms.application.model.RuleDto;
import com.bench.bms.application.service.WarnRuleService;
import com.bench.bms.common.model.BaseRes;
import com.bench.bms.common.model.ValidGroup;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author bench
 * @Date 2024/06/26 11:11
 **/

@Slf4j
@RestController
@RequestMapping("/api")
public class RuleController {

    @Resource
    private WarnRuleService warnRuleService;

    @Resource
    private RuleVoConverter ruleVoConverter;

    @PostMapping("/rule/add")
    public BaseRes<RuleVo> add(@RequestBody RuleReq ruleReq){
        log.info("request add rule: {}.", ruleReq);
        RuleDto ruleDto = ruleVoConverter.toDto(ruleReq);
        ruleDto = warnRuleService.addRule(ruleDto);
        return BaseRes.success(ruleVoConverter.toVo(ruleDto));
    }

    @DeleteMapping("/rule/{id}")
    public BaseRes<Void> delete(@PathVariable(value = "id") Long id){
        log.info("request delete rule by id: {}.", id);
        warnRuleService.remove(id);
        return BaseRes.success();
    }

    @PutMapping("/rule/modify")
    public BaseRes<RuleVo> modify(@Validated(ValidGroup.UpdateGroup.class) @RequestBody RuleReq ruleReq){
        log.info("request modify rule: {}", ruleReq);
        RuleDto ruleDto = ruleVoConverter.toDto(ruleReq);
        warnRuleService.modifyRule(ruleDto);
        return BaseRes.success(ruleVoConverter.toVo(ruleDto));
    }

    @GetMapping("/rule/{id}")
    public BaseRes<RuleVo> findCar(@PathVariable(value = "id") Long id) {
        log.info("request find rule:{}. ", id);
        RuleDto ruleDto = warnRuleService.getRuleById(id);
        log.info("request find user:{}. response", ruleDto);
        return BaseRes.success(ruleVoConverter.toVo(ruleDto));
    }

    @GetMapping("/rule")
    public BaseRes<List<RuleVo>> listAllCar() {
        log.info("request find car:{}. ");
        List<RuleDto> ruleDtoList = warnRuleService.listRules();
        log.info("request find user:{}. response");
        List<RuleVo> ruleVoList = ruleDtoList.stream()
                .map(ruleVoConverter::toVo)
                .collect(Collectors.toList());
        return BaseRes.success(ruleVoList);
    }
}
