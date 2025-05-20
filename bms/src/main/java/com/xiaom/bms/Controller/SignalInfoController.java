package com.xiaom.bms.Controller;

import com.xiaom.bms.Exception.GlobalException;
import com.xiaom.bms.Service.SignalInfoService;
import com.xiaom.bms.common.ResponseResult;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.SignalInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/signal")
public class SignalInfoController {
    @Autowired
    private SignalInfoService signalInfoService;

    @PostMapping("/add")
    public ResponseResult<Integer> createSignalInfo(@RequestBody SignalInfo signalInfo) {
        try {
            int result = signalInfoService.createSignalInfo(signalInfo);
            return ResponseResult.success(result);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseResult<Integer> updateSignalInfo(@PathVariable Long id, @RequestBody SignalInfo signalInfo) {
        try {
            signalInfo.setId(id);
            int result = signalInfoService.updateSignalInfo(signalInfo);
            return ResponseResult.success(result);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PutMapping("/batch")
    public ResponseResult<Integer> batchUpdateSignalInfo(@RequestBody List<SignalInfo> signalInfos) {
        try {
            int result = 0;
            for (SignalInfo signalInfo : signalInfos) {
                result += signalInfoService.updateSignalInfo(signalInfo);
            }
            return ResponseResult.success(result);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseResult<Integer> deleteSignalInfo(@PathVariable Long id) {
        try {
            int result = signalInfoService.deleteSignalInfo(id);
            return ResponseResult.success(result);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseResult<SignalInfo> getSignalInfoById(@PathVariable Long id) {
        try {
            SignalInfo info = signalInfoService.getSignalInfoById(id);
            if (info == null) {
                return ResponseResult.fail(ResponseResultType.NOT_FOUND, "Signal Info not found");
            }
            return ResponseResult.success(info);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping
    public ResponseResult<List<SignalInfo>> getAllSignalInfos() {
        try {
            List<SignalInfo> infos = signalInfoService.getAllSignalInfos();
            return ResponseResult.success(infos);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
