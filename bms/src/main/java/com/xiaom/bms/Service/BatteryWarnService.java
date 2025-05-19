package com.xiaom.bms.Service;

import com.xiaom.bms.model.BatteryWarn;
import com.xiaom.bms.model.WarnReportRequest;

import java.util.List;
import java.util.Map;

public interface BatteryWarnService {
    void reportWarnings(List<WarnReportRequest> requests);
}



