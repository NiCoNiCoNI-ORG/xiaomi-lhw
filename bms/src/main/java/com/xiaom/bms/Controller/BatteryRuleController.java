package com.xiaom.bms.Controller;

import com.xiaom.bms.Exception.GlobalException;
import com.xiaom.bms.Service.BatteryRuleService;
import com.xiaom.bms.common.ResponseResult;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.BatteryRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rule")
public class BatteryRuleController {

    @Autowired
    private BatteryRuleService batteryRuleService;

    @PostMapping("/add")
    public ResponseResult<Void> createRule(@RequestBody BatteryRule batteryRule) {
        try {
            batteryRuleService.createRule(batteryRule);
            return ResponseResult.success();
        } catch (GlobalException e) {
            return ResponseResult.fail(e.getType(), e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseResult.fail(ResponseResultType.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseResult<Void> updateRule(@PathVariable Long id, @RequestBody BatteryRule batteryRule) {
        try {
            batteryRule.setId(id);
            batteryRuleService.updateRule(batteryRule);
            return ResponseResult.success();
        } catch (GlobalException e) {
            return ResponseResult.fail(e.getType(), e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseResult.fail(ResponseResultType.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Void> deleteRule(@PathVariable Long id) {
        try {
            batteryRuleService.deleteRule(id);
            return ResponseResult.success();
        } catch (GlobalException e) {
            return ResponseResult.fail(e.getType(), e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseResult.fail(ResponseResultType.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseResult<BatteryRule> getRuleById(@PathVariable Long id) {
        try {
            BatteryRule rule = batteryRuleService.getRuleById(id);
            if (rule == null) {
                return ResponseResult.fail(ResponseResultType.NOT_FOUND, "Rule not found");
            }
            return ResponseResult.success(rule);
        } catch (GlobalException e) {
            return ResponseResult.fail(e.getType(), e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseResult.fail(ResponseResultType.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseResult<List<BatteryRule>> getAllRules() {
        try {
            List<BatteryRule> rules = batteryRuleService.getAllRules();
            return ResponseResult.success(rules);
        } catch (GlobalException e) {
            return ResponseResult.fail(e.getType(), e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseResult.fail(ResponseResultType.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}