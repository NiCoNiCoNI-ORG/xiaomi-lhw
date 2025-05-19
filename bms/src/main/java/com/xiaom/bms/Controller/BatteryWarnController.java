package com.xiaom.bms.Controller;

import com.xiaom.bms.Exception.GlobalException;
import com.xiaom.bms.Service.BatteryWarnService;
import com.xiaom.bms.common.ResponseResult;
import com.xiaom.bms.common.ResponseResultType;
import com.xiaom.bms.model.BatteryWarn;
import com.xiaom.bms.model.WarnReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BatteryWarnController {
    @Autowired
    private BatteryWarnService batteryWarnService;

    @PostMapping("/warn")
    public ResponseResult<Void> reportWarnings(@RequestBody List<WarnReportRequest> requests) {
        if (requests == null || requests.isEmpty()) {
            return ResponseResult.fail(ResponseResultType.BAD_REQUEST, "Invalid request: empty or null request");
        }
        for (WarnReportRequest request : requests) {
            if (request.getCarId() == null || request.getWarnId() == null) {
            return ResponseResult.fail(ResponseResultType.BAD_REQUEST, "Invalid request: missing or empty required fields");
        }
        }
        try {
            batteryWarnService.reportWarnings(requests);
            return ResponseResult.success(null);
        } catch (GlobalException e) {
            throw e;
        } catch (Exception e) {
            return ResponseResult.fail(ResponseResultType.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
